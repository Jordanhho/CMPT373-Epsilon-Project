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

    public static void createDbUserShift(@Nonnull Integer userId, @Nonnull Integer shiftId) {
        DbUserShift dbUserShift = new DbUserShift(userId, shiftId);
        dbUserShift.save();
    }


    public static void deleteDbUserShiftById(@Nonnull Integer id) {
        DbUserShift dbUserShift = readDbUserShiftById(id);
        dbUserShift.delete();
    }

    public static DbUserShift readDbUserShiftById(@Nonnull Integer id) {
        DbUserShift dbUserShift = DbUserShift.find.byId(id);
        return dbUserShift;
    }

    public static List<DbUserShift> readAllDbUserShift() {
        List<DbUserShift> dbUserShift = DbUserShift.find.all();
        return dbUserShift;
    }
}
