package models.databaseModel.helpers;


import models.databaseModel.scheduling.DbUserShift;

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
     * @param id
     */
    public static void deleteDbUserShiftById(@Nonnull Integer id) {
        DbUserShift dbUserShift = readDbUserShiftById(id);
        dbUserShift.delete();
    }

    /**
     * finds a DbUserShift by UserShiftId
     * @param id
     * @return
     */
    public static DbUserShift readDbUserShiftById(@Nonnull Integer id) {
        DbUserShift dbUserShift = DbUserShift.find.byId(id);
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
                .eq(DbUserShift.COLUMN_USER_ID, userId)
                .findList();

        return dbUserShiftList;
    }
}
