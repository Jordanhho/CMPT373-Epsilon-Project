package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Java Object for DbOneTimeAvailability Table with availbility event id, user id and time block -> start and finish time of event
 */
@Entity
public class DbOneTimeAvailability extends Model {

    public static final String COLUMN_USER_TEAM_ID = "user_team_id";
    public static final String COLUMN_TIME_START = "time_start";
    public static final String COLUMN_TIME_END = "time_end";


    @Id
    private Integer id;

    @Column(nullable = false)
    private Integer userTeamId;

    @Column(nullable = false)
    private Long timeStart;

    @Column(nullable = false)
    private Long timeEnd;

    /**
     * The constructor of the OneTimeAvailibility table
     * the id of this OneTimeAvailibility
     *
     * @param userTeamId    the user Id that uses this OneTimeAvailibility
     * @param timeStart the start time of this OneTimeAvailibility
     * @param timeEnd   the end time of this OneTimeAvailibility
     */
    public DbOneTimeAvailability(Integer userTeamId, Long timeStart, Long timeEnd) {
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

    public static Finder<Integer, DbOneTimeAvailability> find = new Finder<>(DbOneTimeAvailability.class);

    @Override
    public String toString() {
        return "DbOneTimeAvailability{" +
                "id=" + id +
                ", userTeamId=" + userTeamId +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                '}';
    }
}
