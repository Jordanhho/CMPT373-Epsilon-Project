package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbUserTeam;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * CRUD operations for DbUserTeam class
 */
public final class DbUserTeamHelper {

    private DbUserTeamHelper() {

    }

    public static void createDbUserTeam(@Nonnull Integer teamId, Integer userId) {
        DbUserTeam dbUserTeam = new DbUserTeam(teamId, userId);
        dbUserTeam.save();
    }

    public static void deleteDbUserTeamById(@Nonnull Integer id) {
        DbUserTeam dbUserTeam = readDbTeamById(id);
        dbUserTeam.delete();
    }

    public static DbUserTeam readDbTeamById(@Nonnull Integer id) {
        DbUserTeam dbUserTeam = DbUserTeam.find.byId(id);
        return dbUserTeam;
    }

    public static List<DbUserTeam> readAllDbUserTeams() {
        List<DbUserTeam> dbUserTeamList = DbUserTeam.find.all();
        return dbUserTeamList;
    }

}
