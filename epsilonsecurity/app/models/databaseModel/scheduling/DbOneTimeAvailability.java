package models.databaseModel.scheduling;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.ebean.*;

import javax.annotation.Nonnull;

/**
 * Java Object for DbOneTimeAvailability Table with availbility event id, user id and time block -> start and finish time of event
 */
@Entity
public class DbOneTimeAvailability extends Model {

    private static final String COLUMN_USER_TEAM_ID = "user_team_id";
    private static final String COLUMN_TIME_START = "time_start";
    private static final String COLUMN_TIME_END = "time_end";

    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;
    @Nonnull
    private Integer userId;

    @Nonnull
    private Integer timeStart;

    @Nonnull
    private Integer timeEnd;

    /**
     * The constructor of the OneTimeAvailibility table
     * the id of this OneTimeAvailibility
     *
     * @param userId    the user Id that uses this OneTimeAvailibility
     * @param timeStart the start time of this OneTimeAvailibility
     * @param timeEnd   the end time of this OneTimeAvailibility
     */
    public DbOneTimeAvailability(@Nonnull Integer userId, @Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        this.userId = userId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
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
    @Column(name = COLUMN_TIME_START)
    public Integer getTimeStart() {
        return timeStart;
    }

    @Nonnull
    @Column(name = COLUMN_TIME_END)
    public Integer getTimeEnd() {
        return timeEnd;
    }

    @Override
    public String toString() {
        return "DbOneTimeAvailability{" +
                "id=" + id +
                ", userId=" + userId +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                '}';
    }

    public static Finder<Integer, DbOneTimeAvailability> find = new Finder<>(DbOneTimeAvailability.class);

}
