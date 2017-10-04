package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbOneTimeAvailability;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * CRUD operations for DbOneTimeAvailability Class
 */
public final class DbOneTimeAvailabilityHelper {

    private DbOneTimeAvailabilityHelper() {

    }

    /**
     * creates a DbOneTimeAvailability from userId, timeStart, timeEnd
     * @param userId
     * @param timeStart
     * @param timeEnd
     */
    public static void createDbOneTimeAvailability(@Nonnull Integer userId, @Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        DbOneTimeAvailability dbOneTimeAvailability = new DbOneTimeAvailability(userId, timeStart, timeEnd);
        dbOneTimeAvailability.save();
    }


    /**
     * deletes a DbOneTimeAvailability by DbOneTimeAvailabilityId
     * @param id
     */
    public static void deleteDbOneTimeAvailabilityById(@Nonnull Integer id) {
        DbOneTimeAvailability dbOneTimeAvailability = readDbOneTimeAvailabilityById(id);
        dbOneTimeAvailability.delete();
    }

    /**
     * finds a DbOneTimeAvailability by DbOneTimeAvailabilityId
     * @param id
     * @return
     */
    public static DbOneTimeAvailability readDbOneTimeAvailabilityById(@Nonnull Integer id) {
        DbOneTimeAvailability dbOneTimeAvailability = DbOneTimeAvailability.find.byId(id);
        return dbOneTimeAvailability;
    }

    /**
     * returns a list of all DbOneTimeAvailability
     * @return
     */
    public static List<DbOneTimeAvailability> readAllDbOneTimeAvailability() {
        List<DbOneTimeAvailability> dbOneTimeAvailability = DbOneTimeAvailability.find.all();
        return dbOneTimeAvailability;
    }
}
