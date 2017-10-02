package models.databaseModel.scheduling;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.ebean.*;

import javax.annotation.Nonnull;

/**
 * Java Object for DbOneTimeUnavailability Table with unavailability event id, user id and time block -> start and finish time of event
 */
@Entity
public class DbOneTimeUnavailability extends Model {

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
     * The constructor for the OneTimeUnavailability
     * id the id of this OneTimeUnavailability
     *
     * @param userId    the user id of this OneTimeUnavailability
     * @param timeStart the time start of this OneTimeUnavailability
     * @param timeEnd   the time end of this OneTimeUnavailability
     */
    public DbOneTimeUnavailability(@Nonnull Integer userId, @Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
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
        return "DbOneTimeUnavailability{" +
                "id=" + id +
                ", userId=" + userId +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                '}';
    }

    public static Finder<Integer, DbOneTimeUnavailability> find = new Finder<>(DbOneTimeUnavailability.class);

}