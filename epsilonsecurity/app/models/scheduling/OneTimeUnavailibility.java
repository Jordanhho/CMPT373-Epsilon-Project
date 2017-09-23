package models.scheduling;

import models.TimeBlock;
import models.UserIdable;
import models.id.OneTimeAvailabilityId;
import models.id.UserId;

import javax.annotation.Nonnull;

public class OneTimeUnavailibility implements UserIdable {

    @Nonnull private OneTimeAvailabilityId id;
    @Nonnull private UserId userId;
    @Nonnull private TimeBlock timeBlock;

    public OneTimeUnavailibility(@Nonnull OneTimeAvailabilityId id, @Nonnull UserId userId, @Nonnull TimeBlock timeBlock) {
        this.id = id;
        this.userId = userId;
        this.timeBlock = timeBlock;
    }

    public OneTimeAvailabilityId getId() {
        return id;
    }

    public UserId getUserId() { return userId; }

    public TimeBlock getTimeBlock() {
        return timeBlock;
    }
}
