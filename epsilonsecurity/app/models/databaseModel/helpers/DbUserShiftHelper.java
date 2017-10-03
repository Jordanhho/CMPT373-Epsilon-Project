package models.databaseModel.helpers;


import models.databaseModel.scheduling.DbUser;
import models.databaseModel.scheduling.DbUserShift;
import models.databaseModel.scheduling.DbUserTeam;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD operations for DbUserShift class
 */
public final class DbUserShiftHelper {

    private DbUserShiftHelper() {

    }

    public static void createDbUserShift(@Nonnull Integer userTeamId, @Nonnull Integer shiftId) {
        DbUserShift dbUserShift = new DbUserShift(userTeamId, shiftId);
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


    public static List<DbUser> readUserByShiftId(@Nonnull Integer shiftId) {
        List<DbUserShift> dbUserShiftList = DbUserShift.find.query().where().eq(DbUserShift.COLUMN_SHIFT_ID, shiftId).findList();

        List<DbUserTeam> dbUserTeamList = new ArrayList<>();
        for(DbUserShift dbUserShift : dbUserShiftList) {
            dbUserTeamList.add(DbUserTeamHelper.readDbUserTeamById(dbUserShift.getUserTeamId()));
        }

        List<DbUser> dbUserList = new ArrayList<>();
        for(DbUserTeam dbUserTeam : dbUserTeamList){
            dbUserList.add(DbUser.find.byId(dbUserTeam.getUserId()));
        }
        return dbUserList;
    }


}
