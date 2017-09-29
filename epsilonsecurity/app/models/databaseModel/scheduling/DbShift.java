package models.databaseModel.scheduling;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.ebean.*;

/**
 * Java Object for DbShift Table with DbShift event id,
 */
@Entity
public class DbShift extends Model {

    // Fields
    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;

    @Nonnull
    private Integer timeStart;

    @Nonnull
    private Integer timeEnd;

    @Nonnull
    private boolean wasPresent;

    /**
     * The Constructor of this Shift
     * id  the Id of the shift
     * @param timeStart   the start time of the shift
     * @param timeEnd     the end time of the shift
     * @param wasPresent  if the user was present for this shift
     */
    public DbShift(Integer timeStart, Integer timeEnd, boolean wasPresent) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.wasPresent = wasPresent;
    }

    @Nonnull
    public Integer getId() {
        return id;
    }

    @Nonnull
    public Integer getTimeStart() {
        return timeStart;
    }

    @Nonnull
    public Integer getTimeEnd() {
        return timeEnd;
    }

    @Nonnull
    public boolean isWasPresent() {
        return wasPresent;
    }

    @Override
    public String toString() {
        return "DbShift{" +
                "id=" + id +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", wasPresent=" + wasPresent +
                '}';
    }

    public static Finder<Integer, DbShift> find = new Finder<>(DbShift.class);
}