package models.databaseModel.scheduling;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.ebean.*;

/**
 * Java Object for DbShift Table with DbShift event id,
 */
@Entity
public class DbShift extends Model {

    // Fields
    @Id
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
     * @param timeStart   the start time of the shift
     * @param timeEnd     the end time of the shift
     */
    public DbShift(@Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        //wasPresent are set with temporary values
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.wasPresent = true;
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

    public void setWasPresent(boolean wasPresent){
        this.wasPresent = wasPresent;
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