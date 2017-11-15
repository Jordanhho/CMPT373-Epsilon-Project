package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbRecurringAvailability;
import models.databaseModel.scheduling.query.QDbRecurringAvailability;

import java.util.List;


/**
 * CRUD operations for DbRecurringAvailability Class
 */
public final class DbRecurringAvailabilityHelper {

    private DbRecurringAvailabilityHelper() {

    }

    public static void createDbRecurringAvailability(DbRecurringAvailability dbRecurringAvailability) {
        dbRecurringAvailability.save();
    }

    public static void deleteDbRecurringAvailability(DbRecurringAvailability dbRecurringAvailability) {
        dbRecurringAvailability.delete();
    }

    /**
     * finds a DbRecurringAvailability by DbRecurringAvailabilityId
     * @param id
     * @return
     */
    public static DbRecurringAvailability readDbRecurringAvailabilityById(Integer id) {
        DbRecurringAvailability dbRecurringAvailability = new QDbRecurringAvailability()
                .id
                .eq(id)
                .findUnique();

        return dbRecurringAvailability;
    }

    /**
     * returns a list just all DbRecurringAvailability
     * @return
     */
    public static List<DbRecurringAvailability> readAllDbRecurringAvailability() {
        List<DbRecurringAvailability> dbRecurringAvailability = new QDbRecurringAvailability()
                .findList();

        return dbRecurringAvailability;
    }
}
