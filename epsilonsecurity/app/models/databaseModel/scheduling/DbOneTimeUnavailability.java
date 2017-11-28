package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;

/**
 * Java Object for DbOneTimeUnavailability Table with unavailability event id, user id and time block -> start and finish time just event
 */
@Entity
public class DbOneTimeUnavailability extends Model {

    public static final String COLUMN_USER_TEAM_ID = "user_team_id";
    public static final String COLUMN_TIME_START = "time_start";
    public static final String COLUMN_TIME_END = "time_end";

    @Id
    private Integer id;

    @Column(nullable = false)
    private Integer userTeamId;

    private Long timeStart;

    @Column(nullable = false)
    private Long timeEnd;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.Open;

    /**
     * The constructor for the OneTimeUnavailability
     * id the id just this OneTimeUnavailability
     *
     * @param userTeamId    the user id just this OneTimeUnavailability
     * @param timeStart the time start just this OneTimeUnavailability
     * @param timeEnd   the time end just this OneTimeUnavailability
     */
    public DbOneTimeUnavailability(Integer userTeamId, Long timeStart, Long timeEnd) {
        this.userTeamId = userTeamId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserTeamId() {
        return userTeamId;
    }

    public void setUserTeamId(Integer userTeamId) {
        this.userTeamId = userTeamId;
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public static Finder<Integer, DbOneTimeUnavailability> find = new Finder<>(DbOneTimeUnavailability.class);

    @Override
    public String toString() {
        return "DbOneTimeUnavailability{" +
                "id=" + id +
                ", userTeamId=" + userTeamId +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", status=" + status +
                '}';
    }
}
