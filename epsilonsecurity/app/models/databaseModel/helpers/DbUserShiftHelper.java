package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbUserShift;

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
        List<DbUserShift> dbUserShiftList = DbUserShift.find
                .query()
                .where()
                .eq(DbUserShift.COLUMN_SHIFT_ID, shiftId)
                .findList();

        return dbUserShiftList;
    }

    public static List<DbUserShift> readDbUserShiftByUserTeamId(Integer userTeamId) {
        List<DbUserShift> dbUserShiftList = DbUserShift.find
                .query()
                .where()
                .eq(DbUserShift.COLUMN_USER_TEAM_ID, userTeamId)
                .findList();

        return dbUserShiftList;
    }
}
