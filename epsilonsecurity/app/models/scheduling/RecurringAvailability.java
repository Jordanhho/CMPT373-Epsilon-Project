package models.scheduling;

import models.TimeBlock;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.ebean.*;

import javax.annotation.Nonnull;
import java.util.Date;

@Entity
public class RecurringAvailability extends Model {

    @Id
    @Nonnull
    private Integer id;
    @Nonnull
    private Integer userId;

    @Nonnull
    private Date day;
    @Nonnull
    private Integer frequency;

    @Nonnull
    private TimeBlock recurTimeBlock;
    @Nonnull
    private TimeBlock shiftTimeBlock;

    public RecurringAvailability(@Nonnull Integer id, @Nonnull Integer userId, @Nonnull Date day,
                                 @Nonnull Integer frequency, @Nonnull TimeBlock recurTimeBlock,
                                 @Nonnull TimeBlock shiftTimeBlock) {
        this.id = id;
        this.userId = userId;
        this.day = day;
        this.frequency = frequency;
        this.recurTimeBlock = recurTimeBlock;
        this.shiftTimeBlock = shiftTimeBlock;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Date getDay() {
        return day;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public TimeBlock getRecurTimeBlock() {
        return recurTimeBlock;
    }

    public TimeBlock getShiftTimeBlock() {
        return shiftTimeBlock;
    }

    public static Finder<Integer, RecurringAvailability> find = new Finder<>(RecurringAvailability.class);

}
