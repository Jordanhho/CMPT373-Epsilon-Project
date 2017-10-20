package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbTeam;
import models.databaseModel.scheduling.query.QDbTeam;

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
        DbTeam dbTeam = new QDbTeam()
                .id
                .eq(id)
                .findUnique();

        return dbTeam;
    }


    public static DbTeam readDbTeamByName(String name) {
        DbTeam dbTeam = new QDbTeam()
                .name
                .eq(name)
                .findUnique();

        return dbTeam;
    }

    public static List<DbTeam> readAllDbTeam() {
        List<DbTeam> dbTeamList = new QDbTeam()
                .findList();

        return dbTeamList;
    }
}
