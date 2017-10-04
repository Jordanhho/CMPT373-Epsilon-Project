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

    public static void createDbTeam(@Nonnull String name) {
        DbTeam dbTeam = new DbTeam(name);
        dbTeam.save();
    }

    public static void deleteDbTeamById(@Nonnull Integer id) {
        DbTeam dbTeam = readDbTeamById(id);
        dbTeam.delete();
    }

    public static void deleteDbTeamByName(@Nonnull String name) {
        DbTeam dbTeam = DbTeam.find
                .query()
                .where()
                .eq(DbTeam.COLUMN_NAME, name)
                .findOne();

        dbTeam.delete();
    }

    public static DbTeam readDbTeamById(@Nonnull Integer id) {
        DbTeam dbTeam = DbTeam.find.byId(id);
        return dbTeam;
    }

    public static DbTeam readDbTeamByName(@Nonnull String name) {
        DbTeam dbTeam = DbTeam.find
                .query()
                .where()
                .eq(DbTeam.COLUMN_NAME, name)
                .findOne();

        return dbTeam;
    }

    public static List<DbTeam> readAllDbTeam() {
        List<DbTeam> dbTeamList = DbTeam.find.all();
        return dbTeamList;

    }
}
