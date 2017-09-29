package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbOneTimeAvailability;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * CRUD operations for DbOneTimeAvailability Class
 */
public final class DbTeamHelper {

    private DbTeamHelper() {

    }

    public static void createDbOneTimeAvailability(@Nonnull Integer userId, @Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        DbOneTimeAvailability dbOneTimeAvailability = new DbOneTimeAvailability(userId, timeStart, timeEnd);
        dbOneTimeAvailability.save();
    }


    public static void deleteDbOneTimeAvailabilityById(@Nonnull Integer id) {
        DbOneTimeAvailability dbOneTimeAvailability = readDbOneTimeAvailabilityById(id);
        dbOneTimeAvailability.delete();
    }

    public static DbOneTimeAvailability readDbOneTimeAvailabilityById(@Nonnull Integer id) {
        DbOneTimeAvailability dbOneTimeAvailability = DbOneTimeAvailability.find.byId(id);
        return dbOneTimeAvailability;
    }

    public static List<DbOneTimeAvailability> readAllDbOneTimeAvailability() {
        List<DbOneTimeAvailability> dbOneTimeAvailability = DbOneTimeAvailability.find.all();
        return dbOneTimeAvailability;
    }
}
