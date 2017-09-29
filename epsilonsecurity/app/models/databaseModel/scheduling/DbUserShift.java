package models.databaseModel.scheduling;

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

    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;
    @Nonnull
    private Integer userId;
    @Nonnull
    private Integer shiftId;

    /**
     * The Constructor of this UserShift
     * id  the id of this userShift
     * @param userId   the id of the user for this userShift
     * @param shiftId   the id of the shift for this userShift
     */
    public DbUserShift(@Nonnull Integer userId, @Nonnull Integer shiftId) {
        this.userId = userId;
        this.shiftId = shiftId;
    }

    @Nonnull
    public Integer getId() {
        return id;
    }

    @Nonnull
    public Integer getUserId() {
        return userId;
    }

    @Nonnull
    public Integer getShiftId() {
        return shiftId;
    }

    @Override
    public String toString() {
        return "DbUserShift{" +
                "id=" + id +
                ", userId=" + userId +
                ", shiftId=" + shiftId +
                '}';
    }

    public static Finder<Integer, DbUserShift> find = new Finder<>(DbUserShift.class);
}
