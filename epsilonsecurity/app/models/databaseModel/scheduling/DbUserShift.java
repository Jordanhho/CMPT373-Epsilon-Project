package models.databaseModel.scheduling;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.ebean.*;

import javax.annotation.Nonnull;

/**
 * Java Object for DbUserShift Table with DbUserShift event id, user id, shift id
 */
@Entity
public class DbUserShift extends Model {

    public static final String COLUMN_USER_TEAM_ID = "user_team_id";
    public static final String COLUMN_SHIFT_ID = "shift_id";

    public static final String FORM_USER_ID = "userId";
    public static final String FORM_SHIFT_ID = "shiftId";

    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;
    @Nonnull
    private Integer userTeamId;
    @Nonnull
    private Integer shiftId;

    /**
     * The Constructor of this UserShift
     * id  the id of this userShift
     *
     * @param userTeamId  the id of the userTeam for this userShift
     * @param shiftId the id of the shift for this userShift
     */
    public DbUserShift(@Nonnull Integer userTeamId, @Nonnull Integer shiftId) {
        this.userTeamId = userTeamId;
        this.shiftId = shiftId;
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
    @Column(name = COLUMN_SHIFT_ID)
    public Integer getShiftId() {
        return shiftId;
    }

    @Override
    public String toString() {
        return "DbUserShift{" +
                "id=" + id +
                ", userTeamId=" + userTeamId +
                ", shiftId=" + shiftId +
                '}';
    }

    public static Finder<Integer, DbUserShift> find = new Finder<>(DbUserShift.class);
}
