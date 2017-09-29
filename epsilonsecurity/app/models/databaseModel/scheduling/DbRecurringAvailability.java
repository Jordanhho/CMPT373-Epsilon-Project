package models.databaseModel.scheduling;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.ebean.*;

import javax.annotation.Nonnull;
import java.util.Date;



/**
 * Java Object for DbPermissionHelper Table with DbPermissionHelper event id, user id
 * timeBlock-> start and finish time of event of recurrence and shift time event
 * The day of event
 * Frequency of how often this event occurs,
 */
@Entity
public class DbRecurringAvailability extends Model {

    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;

    @Nonnull
    private Integer userId;

    @Nonnull
    private Date day;

    @Nonnull
    private Integer frequency;

    @Nonnull
    private Integer recurTimeStartBlock;

    @Nonnull
    private Integer recurTimeEndBlock;

    @Nonnull
    private Integer shiftTimeStartBlock;

    @Nonnull
    private Integer shiftTimeEndBlock;

    /**
     * The constructor of RecurringAvailability
     * id the id of ths RecurringAvailability
     * @param userId  the user Id for this RecurringAvailability
     * @param day  the day of this RecurringAvailability
     * @param frequency  the frequency of how often this RecurringAvailability occurs
     * @param recurTimeStartBlock  the time start of this recurrence
     * @param recurTimeEndBlock the time end of this recurrence
     * @param shiftTimeStartBlock  the time start of this shift
     * @param shiftTimeEndBlock  the time end of this shift
     */
    public DbRecurringAvailability(
                                   @Nonnull Integer userId,
                                   @Nonnull Date day,
                                   @Nonnull Integer frequency,
                                   @Nonnull Integer recurTimeStartBlock,
                                   @Nonnull Integer recurTimeEndBlock,
                                   @Nonnull Integer shiftTimeStartBlock,
                                   @Nonnull Integer shiftTimeEndBlock) {
        this.userId = userId;
        this.day = day;
        this.frequency = frequency;
        this.recurTimeStartBlock = recurTimeStartBlock;
        this.recurTimeEndBlock = recurTimeEndBlock;
        this.shiftTimeStartBlock = shiftTimeStartBlock;
        this.shiftTimeEndBlock = shiftTimeEndBlock;
    }

    @Nonnull
    public Integer getId() {
        return id;
    }

    @Nonnull
    public Integer getUserId() {
        return userId;
    }

    @Nonnull
    public Date getDay() {
        return day;
    }

    @Nonnull
    public Integer getFrequency() {
        return frequency;
    }

    @Nonnull
    public Integer getRecurTimeStartBlock() {
        return recurTimeStartBlock;
    }

    @Nonnull
    public Integer getRecurTimeEndBlock() {
        return recurTimeEndBlock;
    }

    @Nonnull
    public Integer getShiftTimeStartBlock() {
        return shiftTimeStartBlock;
    }

    @Nonnull
    public Integer getShiftTimeEndBlock() {
        return shiftTimeEndBlock;
    }

    @Override
    public String toString() {
        return "DbPermissionHelper{" +
                "id=" + id +
                ", userId=" + userId +
                ", day=" + day +
                ", frequency=" + frequency +
                ", recurTimeStartBlock=" + recurTimeStartBlock +
                ", recurTimeEndBlock=" + recurTimeEndBlock +
                ", shiftTimeStartBlock=" + shiftTimeStartBlock +
                ", shiftTimeEndBlock=" + shiftTimeEndBlock +
                '}';
    }

    public static Finder<Integer, DbRecurringAvailability> find = new Finder<>(DbRecurringAvailability.class);

}
