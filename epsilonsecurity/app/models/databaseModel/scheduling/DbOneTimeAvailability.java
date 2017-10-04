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

    public static final String COLUMN_USER_TEAM_ID = "user_team_id";
    public static final String COLUMN_TIME_START = "time_start";
    public static final String COLUMN_TIME_END = "time_end";

    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;
    @Nonnull
    private Integer userTeamId;

    @Nonnull
    private Integer timeStart;

    @Nonnull
    private Integer timeEnd;

    /**
     * The constructor of the OneTimeAvailibility table
     * the id of this OneTimeAvailibility
     *
     * @param userTeamId    the user Id that uses this OneTimeAvailibility
     * @param timeStart the start time of this OneTimeAvailibility
     * @param timeEnd   the end time of this OneTimeAvailibility
     */
    public DbOneTimeAvailability(@Nonnull Integer userTeamId, @Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        this.userTeamId = userTeamId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    @Nonnull
    public Integer getId() {
        return id;
    }

    @Nonnull
    @Column(name = COLUMN_USER_TEAM_ID)
    public Integer getUserTeamId() {
        return userTeamId;
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
                ", userTeamId=" + userTeamId +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                '}';
    }

    public static Finder<Integer, DbOneTimeAvailability> find = new Finder<>(DbOneTimeAvailability.class);

}
