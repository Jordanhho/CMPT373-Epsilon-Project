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

    /**
     * creates a DbOneTimeUnavailability from userId, timeStart, timeEnd
     * @param userId
     * @param timeStart
     * @param timeEnd
     */
    public static void createDbOneTimeUnavailability(@Nonnull Integer userId, @Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        DbOneTimeUnavailability dbOneTimeUnavailability = new DbOneTimeUnavailability(userId, timeStart, timeEnd);
        dbOneTimeUnavailability.save();
    }

    /**
     * deletes a DbOneTimeUnavailability from DbOneTimeUnavailabilityId
     * @param id
     */
    public static void deleteDbOneTimeUnavailabilityById(@Nonnull Integer id) {
        DbOneTimeUnavailability dbOneTimeUnavailability = readDbOneTimeUnavailabilityById(id);
        dbOneTimeUnavailability.delete();
    }

    /**
     * finds a DbOneTimeUnavailability from DbOneTimeUnavailabilityId
     * @param id
     */
    public static DbOneTimeUnavailability readDbOneTimeUnavailabilityById(@Nonnull Integer id) {
        DbOneTimeUnavailability dbOneTimeUnavailability = DbOneTimeUnavailability.find.byId(id);
        return dbOneTimeUnavailability;
    }

    /**
     * returns a list of all DbOneTimeUnavailability
     * @return
     */
    public static List<DbOneTimeUnavailability> readAllDbOneTimeUnavailability() {
        List<DbOneTimeUnavailability> dbOneTimeUnavailability = DbOneTimeUnavailability.find.all();
        return dbOneTimeUnavailability;
    }
}
