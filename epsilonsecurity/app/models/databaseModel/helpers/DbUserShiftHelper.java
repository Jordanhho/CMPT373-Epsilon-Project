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

    public static void createDbUserShift(@Nonnull Integer userId, @Nonnull Integer shiftId) {
        DbUserShift dbUserShift = new DbUserShift(userId, shiftId);
        dbUserShift.save();
    }


    public static void deleteDbUserShiftByUserIdAndShiftId(@Nonnull Integer userId, @Nonnull Integer shiftId) {
        DbUserShift dbUserShift = readDbUserShiftById(userId, shiftId);
        dbUserShift.delete();
    }

    public static DbUserShift readDbUserShiftById(@Nonnull Integer userId, @Nonnull Integer shiftId) {
        DbUserShift dbUserShift = DbUserShift.find
                .query()
                .where()
                .conjunction()
                .add(Expr.eq(DbUserShift.COLUMN_USER_ID, userId))
                .add(Expr.eq(DbUserShift.COLUMN_SHIFT_ID, shiftId))
                .findOne();

        return dbUserShift;
    }

    public static List<DbUserShift> readAllDbUserShift() {
        List<DbUserShift> dbUserShift = DbUserShift.find.all();
        return dbUserShift;
    }

    public static List<DbUserShift> readDbUserByShiftId(Integer userId) {
        List<DbUserShift> dbUserShiftList = DbUserShift.find
                .query()
                .where()
                .eq(DbUserShift.COLUMN_USER_ID, userId)
                .findList();

        return dbUserShiftList;
    }
}
