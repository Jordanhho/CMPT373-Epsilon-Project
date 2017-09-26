package models.scheduling;

import models.TimeBlock;

import javax.persistence.Entity;
import javax.persistence.Id;
import io.ebean.*;

import javax.annotation.Nonnull;
@Entity
public class OneTimeUnavailibility extends Model {

    @Id
    @Nonnull private Integer id;
    @Nonnull private Integer userId;
    @Nonnull private TimeBlock timeBlock;

    public OneTimeUnavailibility(@Nonnull Integer id, @Nonnull Integer userId, @Nonnull TimeBlock timeBlock) {
        this.id = id;
        this.userId = userId;
        this.timeBlock = timeBlock;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() { return userId; }

    public TimeBlock getTimeBlock() {
        return timeBlock;
    }

    public static Finder<Integer, OneTimeUnavailibility> find = new Finder<>(OneTimeUnavailibility.class);

}
