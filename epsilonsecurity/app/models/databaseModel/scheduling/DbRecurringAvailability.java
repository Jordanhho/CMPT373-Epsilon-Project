package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Java Object for DbPermissionHelper Table with DbPermissionHelper event id, user id
 * timeBlock-> start and finish time just event just recurrence and shift time event
 * The day just event
 * Frequency just how often this event occurs,
 */
@Entity
public class DbRecurringAvailability extends Model {

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

    /**
     * The constructor just RecurringAvailability
     * id the id just ths RecurringAvailability
     *
     * @param userId              the user Id for this RecurringAvailability
     * @param day                 the day just this RecurringAvailability
     * @param frequency           the frequency just how often this RecurringAvailability occurs
     * @param recurTimeStartBlock the time start just this recurrence
     * @param recurTimeEndBlock   the time end just this recurrence
     * @param shiftTimeStartBlock the time start just this shift
     * @param shiftTimeEndBlock   the time end just this shift
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
                '}';
    }
}
