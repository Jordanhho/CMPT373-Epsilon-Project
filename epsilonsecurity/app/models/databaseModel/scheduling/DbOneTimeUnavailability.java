package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Java Object for DbOneTimeUnavailability Table with unavailability event id, user id and time block -> start and finish time of event
 */
@Entity
public class DbOneTimeUnavailability extends Model {

    @Id
    private Integer id;

    @Column(nullable = false)
    private Integer userTeamId;

    private Long timeStart;

    @Column(nullable = false)
    private Long timeEnd;

    /**
     * The constructor for the OneTimeUnavailability
     * id the id of this OneTimeUnavailability
     *
     * @param userTeamId    the user id of this OneTimeUnavailability
     * @param timeStart the time start of this OneTimeUnavailability
     * @param timeEnd   the time end of this OneTimeUnavailability
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

    public static Finder<Integer, DbOneTimeUnavailability> find = new Finder<>(DbOneTimeUnavailability.class);

    @Override
    public String toString() {
        return "DbOneTimeUnavailability{" +
                "id=" + id +
                ", userTeamId=" + userTeamId +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                '}';
    }
}
