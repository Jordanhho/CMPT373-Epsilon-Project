package models.scheduling;

import models.TimeBlock;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.ebean.*;

import javax.annotation.Nonnull;

@Entity
public class OneTimeAvailability extends Model {

    @Id
    @Nonnull
    private Integer id;
    @Nonnull
    private Integer userId;
    @Nonnull
    private TimeBlock timeBlock;

    public OneTimeAvailability(@Nonnull Integer id, @Nonnull Integer userId, @Nonnull TimeBlock timeBlock) {
        this.id = id;
        this.userId = userId;
        this.timeBlock = timeBlock;
    }

    @Nonnull
    public Integer getUserId() {
        return userId;
    }

    public TimeBlock getTimeBlock() {
        return timeBlock;
    }

    public static Finder<Integer, OneTimeAvailability> find = new Finder<>(OneTimeAvailability.class);

}
