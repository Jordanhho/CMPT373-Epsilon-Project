package models.databaseModel.helpers;

import io.ebean.Expr;
import models.databaseModel.scheduling.DbUserShift;
import models.databaseModel.scheduling.DbUserTeam;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * CRUD operations for DbUserShift class
 */
public final class DbUserShiftHelper {

    private DbUserShiftHelper() {

    }

    /**
     * Creates a dbUserShift from userdId and ShiftId
     * @param userId
     * @param shiftId
     */
    public static void createDbUserShift(@Nonnull Integer userId, @Nonnull Integer shiftId) {
        DbUserShift dbUserShift = new DbUserShift(userId, shiftId);
        dbUserShift.save();
    }

    /**
     * Deletes a UserShift by UserShiftId
     * @param userId
     * @param shiftId
     */
    public static void deleteDbUserShiftByUserIdAndShiftId(@Nonnull Integer userId, @Nonnull Integer shiftId) {
        DbUserShift dbUserShift = readDbUserShiftById(userId, shiftId);
        dbUserShift.delete();
    }

    /**
     * inds a DbUserShift by UserShiftId
     * @param userId
     * @param shiftId
     * @return
     */
    public static DbUserShift readDbUserShiftById(@Nonnull Integer userId, @Nonnull Integer shiftId) {
        DbUserShift dbUserShift = DbUserShift.find
                .query()
                .where()
                .conjunction()
                .add(Expr.eq(DbUserShift.COLUMN_USER_TEAM_ID, userId))
                .add(Expr.eq(DbUserShift.COLUMN_SHIFT_ID, shiftId))
                .findOne();

        return dbUserShift;
    }

    /**
     * returns a list of all DbUserShift
     * @return
     */
    public static List<DbUserShift> readAllDbUserShift() {
        List<DbUserShift> dbUserShift = DbUserShift.find.all();
        return dbUserShift;
    }

    /**
     * returns a list of DbUserShift by UserId
     * @param userId
     * @return
     */
    public static List<DbUserShift> readDbUserByShiftId(Integer userId) {
        List<DbUserShift> dbUserShiftList = DbUserShift.find
                .query()
                .where()
                .eq(DbUserShift.COLUMN_USER_TEAM_ID, userId)
                .findList();

        return dbUserShiftList;
    }
}
