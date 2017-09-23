package models.scheduling;

import models.UserIdable;
import models.id.ShiftId;
import models.id.UserId;
import models.id.UserShiftId;

import javax.annotation.Nonnull;

public class UserShift implements UserIdable {
    @Nonnull private UserShiftId id;
    @Nonnull private UserId userId;
    @Nonnull private ShiftId shiftId;

    public UserShift(@Nonnull UserShiftId id, @Nonnull UserId userId, @Nonnull ShiftId shiftId) {
        this.id = id;
        this.userId = userId;
        this.shiftId = shiftId;
    }

    @Nonnull
    public UserShiftId getId() {
        return id;
    }

    @Nonnull
    public ShiftId getShiftId() {
        return shiftId;
    }

    @Nonnull
    @Override
    public UserId getUserId() {
        return userId;
    }

}
