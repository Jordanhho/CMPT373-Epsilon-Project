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

    /**
     * Creates a dbUser from teamId and userId
     * @param teamId
     * @param userId
     */
    public static void createDbUserTeam(@Nonnull Integer teamId, Integer userId) {
        DbUserTeam dbUserTeam = new DbUserTeam(teamId, userId);
        dbUserTeam.save();
    }

    /**
     * Deletes a DbUserTeam by DbUserTeam id
     * @param id
     */
    public static void deleteDbUserTeamById(@Nonnull Integer id) {
        DbUserTeam dbUserTeam = readDbTeamById(id);
        dbUserTeam.delete();
    }

    /**
     * Searches for a dbUserTeam by dbUserTeam id
     * @param id
     * @return
     */
    public static DbUserTeam readDbTeamById(@Nonnull Integer id) {
        DbUserTeam dbUserTeam = DbUserTeam.find.byId(id);
        return dbUserTeam;
    }

    /**
     * obtains list of all DbUserTeam
     * @return
     */
    public static List<DbUserTeam> readAllDbUserTeams() {
        List<DbUserTeam> dbUserTeamList = DbUserTeam.find.all();
        return dbUserTeamList;
    }

}
