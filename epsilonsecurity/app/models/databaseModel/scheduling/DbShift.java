package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Java Object for DbShift Table with DbShift event id,
 */
@Entity
public class DbShift extends Model {

    public static final String COLUMN_SHIFT_TYPE_ID = "shift_type_id";
    public static final String COLUMN_TIME_START = "time_start";
    public static final String COLUMN_TIME_END = "time_end";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_WAS_PRESENT = "was_present";

    @Id
    private Integer id;

    @Column(nullable = false)
    private Integer shiftTypeId;

    @Column(nullable = false)
    private Long timeStart;

    @Column(nullable = false)
    private Long timeEnd;

    @Column
    private String description;

    private boolean wasPresent;

<<<<<<< HEAD
    /**
     * The Constructor just this Shift
     * id  the Id just the shift
     *
     * @param timeStart the start time just the shift
     * @param timeEnd   the end time just the shift
     *                  wasPresent  if the user was present for this shift
     */
=======
>>>>>>> master
    public DbShift(Integer shiftTypeId, Long timeStart, Long timeEnd) {
        this.shiftTypeId = shiftTypeId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.wasPresent = true;
    }

    public DbShift(Integer shiftTypeId, Long timeStart, Long timeEnd, String description) {
        this.shiftTypeId = shiftTypeId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.wasPresent = true;
        this.description = description;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShiftTypeId() {
        return shiftTypeId;
    }

    public void setShiftTypeId(Integer shiftTypeId) {
        this.shiftTypeId = shiftTypeId;
    }

    public Long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Long timeStart) {
        this.timeStart = timeStart;
    }

    public Long getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Long timeEnd) {
        this.timeEnd = timeEnd;
    }

    public boolean isWasPresent() {
        return wasPresent;
    }

    public void setWasPresent(boolean wasPresent) {
        this.wasPresent = wasPresent;
    }

    public static Finder<Integer, DbShift> find = new Finder<>(DbShift.class);

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "DbShift{" +
                "id=" + id +
                ", shiftTypeId=" + shiftTypeId +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", wasPresent=" + wasPresent +
                '}';
    }
}