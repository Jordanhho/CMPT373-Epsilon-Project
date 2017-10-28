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

    @Id
    private Integer id;

    @Column(nullable = false)
    private Integer shiftTypeId;

    @Column(nullable = false)
    private Long timeStart;

    @Column(nullable = false)
    private Long timeEnd;

    private boolean wasPresent;

    /**
     * The Constructor of this Shift
     * id  the Id of the shift
     *
     * @param timeStart the start time of the shift
     * @param timeEnd   the end time of the shift
     *                  wasPresent  if the user was present for this shift
     */
    public DbShift(Integer shiftTypeId, Long timeStart, Long timeEnd) {
        this.shiftTypeId = shiftTypeId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.wasPresent = true;
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