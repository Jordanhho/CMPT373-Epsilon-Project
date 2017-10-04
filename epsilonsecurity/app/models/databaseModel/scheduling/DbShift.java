package models.databaseModel.scheduling;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.ebean.*;

/**
 * Java Object for DbShift Table with DbShift event id,
 */
@Entity
public class DbShift extends Model {

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TIME_START = "time_start";
    public static final String COLUMN_TIME_END = "time_end";
    public static final String COLUMN_WAS_PRESENT = "was_present";


    public static final String FORM_COLUMN_NAME = "name";
    public static final String FORM_COLUMN_TIME_START = "timeStart";
    public static final String FORM_COLUMN_TIME_END = "timeEnd";
    public static final String FORM_COLUMN_WAS_PRESENT = "wasPresent";

    // Fields
    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;

    @Nonnull
    String name;

    @Nonnull
    private Integer timeStart;

    @Nonnull
    private Integer timeEnd;

    @Nonnull
    private boolean wasPresent;

    /**
     * The Constructor of this Shift
     * id  the Id of the shift
     *
     * @param timeStart the start time of the shift
     * @param timeEnd   the end time of the shift
     *                  wasPresent  if the user was present for this shift
     */
    public DbShift(@Nonnull String name, @Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        //wasPresent are set with temporary values
        this.name = name;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.wasPresent = true;
    }

    @Nonnull
    public Integer getId() {
        return id;
    }

    @Nonnull
    @Column(name = COLUMN_NAME)
    public String getName() {
        return name;
    }

    @Nonnull
    @Column(name = COLUMN_TIME_START)
    public Integer getTimeStart() {
        return timeStart;
    }

    @Nonnull
    @Column(name = COLUMN_TIME_END)
    public Integer getTimeEnd() {
        return timeEnd;
    }

    public void setWasPresent(boolean wasPresent) {
        this.wasPresent = wasPresent;
    }

    @Nonnull
    @Column(name = COLUMN_WAS_PRESENT)
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