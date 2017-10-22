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
    private String name;

    @Column(nullable = false)
    private Integer timeStart;

    @Column(nullable = false)
    private Integer timeEnd;

    private boolean wasPresent;

    /**
     * The Constructor of this Shift
     * id  the Id of the shift
     *
     * @param timeStart the start time of the shift
     * @param timeEnd   the end time of the shift
     *                  wasPresent  if the user was present for this shift
     */
    public DbShift(String name, Integer timeStart, Integer timeEnd) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Integer timeStart) {
        this.timeStart = timeStart;
    }

    public Integer getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Integer timeEnd) {
        this.timeEnd = timeEnd;
    }

    public boolean isWasPresent() {
        return wasPresent;
    }

    public void setWasPresent(boolean wasPresent) {
        this.wasPresent = wasPresent;
    }

    public static Finder<Integer, DbShift> find = new Finder<>(DbShift.class);
}