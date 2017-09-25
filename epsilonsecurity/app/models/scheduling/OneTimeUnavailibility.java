package models.scheduling;

import models.TimeBlock;
import models.UserIdable;
import models.id.OneTimeAvailabilityId;
import models.id.UserId;
import javax.persistence.Entity;
import javax.persistence.Id;
import io.ebean.*;

import javax.annotation.Nonnull;
@Entity
public class OneTimeUnavailibility implements UserIdable extends Model {
    @Id
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

    public static Finder<Integer, OneTimeUnavailibility> find = new Finder<>(OneTimeUnavailibility.class);

}
