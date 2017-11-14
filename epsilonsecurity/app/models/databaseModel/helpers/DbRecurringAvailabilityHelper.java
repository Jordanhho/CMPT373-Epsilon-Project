package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbRecurringAvailability;

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

    public static DbRecurringAvailability readDbRecurringAvailabilityById(Integer id) {
        return DbRecurringAvailability.find.byId(id);
    }

    public static List<DbRecurringAvailability> readAllDbRecurringAvailability() {
        return DbRecurringAvailability.find.all();
    }
}
