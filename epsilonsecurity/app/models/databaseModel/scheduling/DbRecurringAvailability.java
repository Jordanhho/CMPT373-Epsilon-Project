package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;


/**
 * Java Object for DbPermissionHelper Table with DbPermissionHelper event id, user id
 * timeBlock-> start and finish time of event of recurrence and shift time event
 * The day of event
 * Frequency of how often this event occurs,
 */
@Entity
public class DbRecurringAvailability extends Model {

    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_FREQUENCY = "frequency";
    public static final String COLUMN_RECUR_TIME_START_BLOCK = "recur_time_start_block";
    public static final String COLUMN_RECUR_TIME_END_BLOCK = "recur_time_end_block";
    public static final String COLUMN_SHIFT_TIME_START_BLOCK = "shift_time_start_block";
    public static final String COLUMN_SHIFT_TIME_END_BLOCK = "shift_time_end_block";

    @Id
    private Integer id;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Day day;

    @Column(nullable = false)
    private Integer frequency;

    @Column(nullable = false)
    private Long recurTimeStartBlock;

    @Column(nullable = false)
    private Long recurTimeEndBlock;

    @Column(nullable = false)
    private Long shiftTimeStartBlock;

    @Column(nullable = false)
    private Long shiftTimeEndBlock;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.Open;

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
    public DbRecurringAvailability(Integer userId,
                                   Day day,
                                   Integer frequency,
                                   Long recurTimeStartBlock,
                                   Long recurTimeEndBlock,
                                   Long shiftTimeStartBlock,
                                   Long shiftTimeEndBlock) {
        this.userId = userId;
        this.day = day;
        this.frequency = frequency;
        this.recurTimeStartBlock = recurTimeStartBlock;
        this.recurTimeEndBlock = recurTimeEndBlock;
        this.shiftTimeStartBlock = shiftTimeStartBlock;
        this.shiftTimeEndBlock = shiftTimeEndBlock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Long getRecurTimeStartBlock() {
        return recurTimeStartBlock;
    }

    public void setRecurTimeStartBlock(Long recurTimeStartBlock) {
        this.recurTimeStartBlock = recurTimeStartBlock;
    }

    public Long getRecurTimeEndBlock() {
        return recurTimeEndBlock;
    }

    public void setRecurTimeEndBlock(Long recurTimeEndBlock) {
        this.recurTimeEndBlock = recurTimeEndBlock;
    }

    public Long getShiftTimeStartBlock() {
        return shiftTimeStartBlock;
    }

    public void setShiftTimeStartBlock(Long shiftTimeStartBlock) {
        this.shiftTimeStartBlock = shiftTimeStartBlock;
    }

    public Long getShiftTimeEndBlock() {
        return shiftTimeEndBlock;
    }

    public void setShiftTimeEndBlock(Long shiftTimeEndBlock) {
        this.shiftTimeEndBlock = shiftTimeEndBlock;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public static Finder<Integer, DbRecurringAvailability> find = new Finder<>(DbRecurringAvailability.class);

    @Override
    public String toString() {
        return "DbRecurringAvailability{" +
                "id=" + id +
                ", userId=" + userId +
                ", day=" + day +
                ", frequency=" + frequency +
                ", recurTimeStartBlock=" + recurTimeStartBlock +
                ", recurTimeEndBlock=" + recurTimeEndBlock +
                ", shiftTimeStartBlock=" + shiftTimeStartBlock +
                ", shiftTimeEndBlock=" + shiftTimeEndBlock +
                ", status=" + status +
                '}';
    }
}
