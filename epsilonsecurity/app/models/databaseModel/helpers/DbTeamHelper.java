package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbTeam;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * CRUD operations for DbTeam class
 */
public final class DbTeamHelper {

    private DbTeamHelper() {

    }

    /**
     * creates a DbTeam from name
     * @param name
     */
    public static void createDbTeam(@Nonnull String name) {
        DbTeam dbTeam = new DbTeam(name);
        dbTeam.save();
    }

    /**
     * Deletes a DbTeam by teamId
     * @param id
     */
    public static void deleteDbTeamById(@Nonnull Integer id) {
        DbTeam dbTeam = readDbTeamById(id);
        dbTeam.delete();
    }

    /**
     * finds a DbTeam by teamId
     * @param id
     * @return
     */
    public static DbTeam readDbTeamById(@Nonnull Integer id) {
        DbTeam dbTeam = DbTeam.find.byId(id);
        return dbTeam;
    }

    /**
     * Returns a list of all DbTeam
     * @return
     */
    public static List<DbTeam> readAllDbTeam() {
        List<DbTeam> dbTeamList = DbTeam.find.all();
        return dbTeamList;

    }
}
