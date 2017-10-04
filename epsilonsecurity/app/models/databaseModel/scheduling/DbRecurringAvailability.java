package models.databaseModel.scheduling;

import javax.persistence.Column;
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

    private static final String COLUMN_USER_TEAM_ID = "user_team_id";
    private static final String COLUMN_DAY = "day";
    private static final String COLUMN_FREQUENCY = "frequency";
    private static final String COLUMN_RECURRING_TIME_START_BLOCK = "recur_time_start_block";
    private static final String COLUMN_RECURRING_TIME_END_BLOCK = "recur_time_start_block";
    private static final String COLUMN_SHIFT_TIME_START_BLOCK = "shift_time_start_block";
    private static final String COLUMN_SHIFT_TIME_END_BLOCK = "shift_time_end_block";

    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;

    @Nonnull
    private Integer userId;

    @Nonnull
    private Day day;

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
     *
     * @param userId              the user Id for this RecurringAvailability
     * @param day                 the day of this RecurringAvailability
     * @param frequency           the frequency of how often this RecurringAvailability occurs
     * @param recurTimeStartBlock the time start of this recurrence
     * @param recurTimeEndBlock   the time end of this recurrence
     * @param shiftTimeStartBlock the time start of this shift
     * @param shiftTimeEndBlock   the time end of this shift
     */
    public DbRecurringAvailability(
            @Nonnull Integer userId,
            @Nonnull Day day,
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
    @Column(name = COLUMN_USER_TEAM_ID)
    public Integer getUserId() {
        return userId;
    }

    @Nonnull
    @Column(name = COLUMN_DAY)
    public Day getDay() {
        return day;
    }

    @Nonnull
    @Column(name = COLUMN_FREQUENCY)
    public Integer getFrequency() {
        return frequency;
    }

    @Nonnull
    @Column(name = COLUMN_RECURRING_TIME_START_BLOCK)
    public Integer getRecurTimeStartBlock() {
        return recurTimeStartBlock;
    }

    @Nonnull
    @Column(name = COLUMN_RECURRING_TIME_END_BLOCK)
    public Integer getRecurTimeEndBlock() {
        return recurTimeEndBlock;
    }

    @Nonnull
    @Column(name = COLUMN_SHIFT_TIME_START_BLOCK)
    public Integer getShiftTimeStartBlock() {
        return shiftTimeStartBlock;
    }

    @Nonnull
    @Column(name = COLUMN_SHIFT_TIME_END_BLOCK)
    public Integer getShiftTimeEndBlock() {
        return shiftTimeEndBlock;
    }

    @Override
    public String toString() {
        return "DbPermissionHelper{" +
                "id=" + id +
                ", userId=" + userId +
                ", day=" + day.toString() +
                ", frequency=" + frequency +
                ", recurTimeStartBlock=" + recurTimeStartBlock +
                ", recurTimeEndBlock=" + recurTimeEndBlock +
                ", shiftTimeStartBlock=" + shiftTimeStartBlock +
                ", shiftTimeEndBlock=" + shiftTimeEndBlock +
                '}';
    }

    public static Finder<Integer, DbRecurringAvailability> find = new Finder<>(DbRecurringAvailability.class);

}
