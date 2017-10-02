package models.databaseModel.helpers;



import models.databaseModel.scheduling.DbOneTimeUnavailability;

import javax.annotation.Nonnull;
import java.util.List;


/**
 * CRUD operations for DbOneTimeUnavailability Class
 */
public final class DbOneTimeUnavailabilityHelper {

    private DbOneTimeUnavailabilityHelper() {

    }

    public static void createDbOneTimeUnavailability(@Nonnull Integer userId, @Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        DbOneTimeUnavailability dbOneTimeUnavailability = new DbOneTimeUnavailability(userId, timeStart, timeEnd);
        dbOneTimeUnavailability.save();
    }


    public static void deleteDbOneTimeUnavailabilityById(@Nonnull Integer id) {
        DbOneTimeUnavailability dbOneTimeUnavailability = readDbOneTimeUnavailabilityById(id);
        dbOneTimeUnavailability.delete();
    }

    public static DbOneTimeUnavailability readDbOneTimeUnavailabilityById(@Nonnull Integer id) {
        DbOneTimeUnavailability dbOneTimeUnavailability = DbOneTimeUnavailability.find.byId(id);
        return dbOneTimeUnavailability;
    }

    public static List<DbOneTimeUnavailability> readAllDbOneTimeUnavailability() {
        List<DbOneTimeUnavailability> dbOneTimeUnavailability = DbOneTimeUnavailability.find.all();
        return dbOneTimeUnavailability;
    }
}
