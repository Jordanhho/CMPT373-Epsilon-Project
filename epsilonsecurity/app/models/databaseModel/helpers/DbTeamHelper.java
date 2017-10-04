package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbShiftName;
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
     * Deletes a DbTeam by name
     * @param name
     */
    public static void deleteDbTeamByName(@Nonnull String name) {
        DbTeam dbTeam = DbTeam.find
                .query()
                .where()
                .eq(DbTeam.COLUMN_NAME, name)
                .findOne();

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
     * finds a DbTeam by name
     * @param name
     * @return
     */
    public static DbTeam readDbTeamByName(@Nonnull String name) {
        DbTeam dbTeam = DbTeam.find
                .query()
                .where()
                .eq(DbTeam.COLUMN_NAME, name)
                .findOne();

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
