package store;

import models.databaseModel.helpers.DbShiftHelper;
import models.databaseModel.helpers.DbShiftTypeHelper;
import models.databaseModel.scheduling.DbShift;
import models.databaseModel.scheduling.DbShiftType;
import models.schedule.*;
import org.javatuples.Pair;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

public class ScheduleStore extends DataStore {

    private static ScheduleStore instance = new ScheduleStore();

    public static ScheduleStore getInstance() {
        return instance;
    }

    public CompletableFuture<List<Shift>> getShiftsInRange(Instant start, Instant end) {
        return runAsync(() -> {
           Long startSec = start.getEpochSecond();
           Long endSec = end.getEpochSecond();
            List<DbShift> dbShifts = DbShiftHelper.readDbShiftByTime(startSec, endSec);
            List<Pair<DbShift, DbShiftType>> shiftAndTypePairs = dbShifts.stream()
                .map( shift -> {
                    DbShiftType shiftType = DbShiftTypeHelper.readDbShiftTypeById(shift.getShiftTypeId());
                    return Pair.with(shift, shiftType);
                })
                .collect(toList());

            return shiftAndTypePairs;
        })
            .thenCompose( shiftAndTypePairs -> {
                List<CompletableFuture<Shift>> shiftFutures = shiftAndTypePairs.stream()
                    .map(this::getShiftFromShiftTypePair)
                    .collect(toList());
                return sequence(shiftFutures);
            });
    }

    private CompletableFuture<Shift> getShiftFromShiftTypePair(Pair<DbShift, DbShiftType> shiftTypePair) {
        DbShift dbShift = shiftTypePair.getValue0();
        ShiftId shiftId = new ShiftId(dbShift.getId());
        DbShiftType dbShiftType = shiftTypePair.getValue1();
        List<TimeSlot> timeSlots = TimeSlotFactory.newTimeSlots(Instant.ofEpochSecond(dbShift.getTimeStart()),
                                                                Instant.ofEpochSecond(dbShift.getTimeEnd()));
        TimeBlock timeBlock = new TimeBlock(timeSlots);
        ShiftType shiftType = new ShiftType(shiftTypePair.getValue1());
        return UserStore.getInstance().getUserForShift(shiftId)
            .thenApply( user -> new Shift(user, timeBlock, shiftId, shiftType));
    }
}
