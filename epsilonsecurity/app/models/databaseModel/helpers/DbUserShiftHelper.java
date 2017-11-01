package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbUserShift;
import models.databaseModel.scheduling.query.QDbUserShift;

import java.util.List;

/**
 * CRUD operations for DbUserShift class
 */
public final class DbUserShiftHelper {

    private DbUserShiftHelper() {

    }

    public static void createDbUserShift(DbUserShift dbUserShift) {
        dbUserShift.save();
    }

    public static void deleteDbUserShift(DbUserShift dbUserShift) {
        dbUserShift.delete();
    }

    public static void deleteDbUserShifts(List<DbUserShift> dbUserShifts) {
        for (DbUserShift dbUserShift : dbUserShifts) {
            dbUserShift.delete();
        }
    }

    public static List<DbUserShift> readDbUserShiftByShiftId(Integer shiftId) {
        List<DbUserShift> dbUserShiftList = new QDbUserShift()
                .shiftId
                .eq(shiftId)
                .findList();

        return dbUserShiftList;
    }

    public static List<DbUserShift> readDbUserShiftByUserId(Integer userId) {
        List<DbUserShift> dbUserShiftList = new QDbUserShift()
                .userTeamId
                .eq(userId)
                .findList();

        return dbUserShiftList;
    }
}
