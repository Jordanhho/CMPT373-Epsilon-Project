package models.scheduling;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.ebean.*;

import javax.annotation.Nonnull;


@Entity
public class UserShift extends Model {

    @Id
    @Nonnull
    private Integer id;
    @Nonnull
    private Integer userId;
    @Nonnull
    private Integer shiftId;

    public UserShift(@Nonnull Integer id, @Nonnull Integer userId, @Nonnull Integer shiftId) {
        this.id = id;
        this.userId = userId;
        this.shiftId = shiftId;
    }

    @Nonnull
    public Integer getId() {
        return id;
    }

    @Nonnull
    public Integer getShiftId() {
        return shiftId;
    }

    @Nonnull
    public Integer getUserId() {
        return userId;
    }


    public static Finder<Integer, UserShift> find = new Finder<>(UserShift.class);
}
