package models.scheduling;

import models.TimeBlock;
import models.UserIdable;
import models.id.OneTimeAvailabilityId;
import models.id.UserId;

import javax.annotation.Nonnull;

public class OneTimeAvailability implements UserIdable {

    @Nonnull private OneTimeAvailabilityId id;
    @Nonnull private UserId userId;
    @Nonnull private TimeBlock timeBlock;

    public OneTimeAvailability(@Nonnull OneTimeAvailabilityId id,@Nonnull  UserId userId, @Nonnull TimeBlock timeBlock) {
        this.id = id;
        this.userId = userId;
        this.timeBlock = timeBlock;
    }

    @Nonnull
    @Override
    public UserId getUserId() { return userId; }

    public TimeBlock getTimeBlock() {
        return timeBlock;
    }
}
