package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbRecurringAvailability;

import javax.annotation.Nonnull;
import java.util.Date;
import java.util.List;


/**
 * CRUD operations for DbRecurringAvailability Class
 */
public final class DbRecurringAvailabilityHelper {

    private DbRecurringAvailabilityHelper() {

    }

    /**
     * creates a DbRecurringAvailability from userId, Day, Frequency, recurTimeStartBlock, recurTimeEndBlock, shiftTimeStartBlock, shiftTimeEndBlock
     * @param userId
     * @param day
     * @param frequency
     * @param recurTimeStartBlock
     * @param recurTimeEndBlock
     * @param shiftTimeStartBlock
     * @param shiftTimeEndBlock
     */
    public static void createDbRecurringAvailability(@Nonnull Integer userId,
                                                     @Nonnull Date day,
                                                     @Nonnull Integer frequency,
                                                     @Nonnull Integer recurTimeStartBlock,
                                                     @Nonnull Integer recurTimeEndBlock,
                                                     @Nonnull Integer shiftTimeStartBlock,
                                                     @Nonnull Integer shiftTimeEndBlock) {
        DbRecurringAvailability dbRecurringAvailability = new DbRecurringAvailability(userId, day, frequency, recurTimeStartBlock, recurTimeEndBlock, shiftTimeStartBlock, shiftTimeEndBlock);
        dbRecurringAvailability.save();
    }

    /**
     * deletes a DbRecurringAvailability by DbRecurringAvailabilityId
     * @param id
     */
    public static void deleteDbRecurringAvailabilityById(Integer id) {
        DbRecurringAvailability dbRecurringAvailability = readDbRecurringAvailabilityById(id);
        dbRecurringAvailability.delete();
    }

    /**
     * finds a DbRecurringAvailability by DbRecurringAvailabilityId
     * @param id
     * @return
     */
    public static DbRecurringAvailability readDbRecurringAvailabilityById(Integer id) {
        DbRecurringAvailability dbRecurringAvailability = DbRecurringAvailability.find.byId(id);
        return dbRecurringAvailability;
    }

    /**
     * returns a list of all DbRecurringAvailability
     * @return
     */
    public static List<DbRecurringAvailability> readAllDbRecurringAvailability() {
        List<DbRecurringAvailability> dbRecurringAvailability = DbRecurringAvailability.find.all();
        return dbRecurringAvailability;
    }
}
