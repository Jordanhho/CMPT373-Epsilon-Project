package models.schedule;

import store.ShiftId;
import store.UserId;
import store.UserIdable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class Shift
        implements UserIdable {

    // Fields
    @Nonnull private UserId userId;
    @Nonnull private TimeBlock timeBlock;
    @Nonnull private ShiftId shiftId;
    @Nonnull private ShiftType shiftType;

    // Constructors
    @Nonnull
    public Shift(@Nonnull UserIdable userIDable, @Nonnull TimeBlock timeBlock, @Nonnull ShiftId shiftId, ShiftType shiftType) {
        this.userId = userIDable.getUserId();
        this.timeBlock = timeBlock;
        this.shiftId = shiftId;
        this.shiftType = shiftType;
    }

    // Getters
    @Override
    public UserId getUserId() {
        return userId;
    }

    @Nonnull
    public TimeBlock getTimeBlock() {
        return timeBlock;
    }

    // Private Interface
    @Nonnull
    private Shift copy(@Nonnull Shift shift) {
        return new Shift(shift.userId, shift.timeBlock, shift.shiftId, shift.shiftType);
    }

}