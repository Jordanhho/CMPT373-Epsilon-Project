package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbTeam;

import java.util.List;

/**
 * CRUD operations for DbTeam class
 */
public final class DbTeamHelper {


    private DbTeamHelper() {

    }


    public static void createDbTeam(DbTeam dbTeam) {
        dbTeam.save();
    }


    public static void deleteDbTeamByName(DbTeam dbTeam) {
        dbTeam.delete();
    }


    public static DbTeam readDbTeamById(Integer id) {
        return DbTeam.find.byId(id);
    }


    public static DbTeam readDbTeamByName(String name) {
        DbTeam dbTeam = DbTeam.find
                .query()
                .where()
                .eq(DbTeam.COLUMN_NAME, name)
                .findUnique();

        return dbTeam;
    }

    public static List<DbTeam> readAllDbTeam() {
        return DbTeam.find.all();
    }
}
