package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Java Object for DbUserShift Table with DbUserShift event id, user id, shift id
 */
@Entity
public class DbUserShift extends Model {

    @Id
    private Integer id;

    @Column(nullable = false)
    private Integer userTeamId;

    @Column(nullable = false)
    private Integer shiftId;

    /**
     * The Constructor of this UserShift
     * id  the id of this userShift
     *
     * @param userTeamId  the id of the userTeam for this userShift
     * @param shiftId the id of the shift for this userShift
     */
    public DbUserShift(Integer userTeamId, Integer shiftId) {
        this.userTeamId = userTeamId;
        this.shiftId = shiftId;
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

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public static Finder<Integer, DbUserShift> find = new Finder<>(DbUserShift.class);

    @Override
    public String toString() {
        return "DbUserShift{" +
                "id=" + id +
                ", userTeamId=" + userTeamId +
                ", shiftId=" + shiftId +
                '}';
    }
}
