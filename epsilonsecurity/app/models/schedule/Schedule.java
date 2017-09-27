package models.schedule;

import models.UserId;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import org.javatuples.KeyValue;

public class Schedule {

    private Map<TimeBlock, Set<UserId>> userTimeBlocks = new HashMap<>();

    public void createTimeBlock(Instant start, Instant end) {
        // TODO:
        // precondition: this time block does not yet exist (check)
        TimeBlock timeBlock = new TimeBlock(start, end);
        userTimeBlocks.put(timeBlock, new HashSet<>());
    }

    public void assignUserToTimeBlock(UserId user, TimeBlock timeBlock) {
        // TODO:
        // precondition: check that shift exists
        Set<UserId> users = getUsersForTimeBlock(timeBlock);
        users.add(user);
        putUsersForTimeBlock(users, timeBlock);
    }

    public void removeUserFromTimeBlock(UserId user, TimeBlock timeBlock) {
        Set<UserId> users = getUsersForTimeBlock(timeBlock);
        users.remove(user);
        putUsersForTimeBlock(users, timeBlock);
    }

    public List<TimeBlock> getTimeBlocks() {
        return new ArrayList<TimeBlock>(userTimeBlocks.keySet());
    }

    public void removeTimeBlock(TimeBlock timeBlock) {
        userTimeBlocks.remove(timeBlock);
    }

    public Set<Shift> getShifts() {
        List<Map.Entry<TimeBlock, Set<UserId>>> entriesList = new ArrayList<>(userTimeBlocks.entrySet());

        Set<Shift> shifts = entriesList.stream()
            .map( x -> KeyValue.with(x.getKey(), x.getValue()))
            .flatMap( keyValue -> {
                List<UserId> userIds = new ArrayList<UserId>(keyValue.getValue());
                return userIds.stream()
                    .map( userId -> KeyValue.with(keyValue.getKey(), userId) );
            })
            .map( timeBlockUserId -> {
                TimeBlock timeBlock = timeBlockUserId.getKey();
                UserId userId = timeBlockUserId.getValue();
                return new Shift(userId, timeBlock, Optional.empty());
            })
            .collect(Collectors.toSet());

        return shifts;
    }

    private Set<UserId> getUsersForTimeBlock(TimeBlock timeBlock) {
        return userTimeBlocks.get(timeBlock);
    }

    private void putUsersForTimeBlock(Set<UserId> users, TimeBlock timeBlock) {
        userTimeBlocks.put(timeBlock, users);
    }


}
