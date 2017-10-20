package models.databaseModel.helpers;


import models.databaseModel.scheduling.DbOneTimeUnavailability;
import models.databaseModel.scheduling.query.QDbOneTimeUnavailability;

import java.util.List;


/**
 * CRUD operations for DbOneTimeUnavailability Class
 */
public final class DbOneTimeUnavailabilityHelper {

    private DbOneTimeUnavailabilityHelper() {

    }

    public static void createDbOneTimeUnavailability(DbOneTimeUnavailability dbOneTimeUnavailability) {
        dbOneTimeUnavailability.save();
    }

    public static void deleteDbOneTimeUnavailabilityById(DbOneTimeUnavailability dbOneTimeUnavailability) {
        dbOneTimeUnavailability.delete();
    }

    /**
     * finds a DbOneTimeUnavailability from DbOneTimeUnavailabilityId
     *
     * @param id
     */
    public static DbOneTimeUnavailability readDbOneTimeUnavailabilityById(Integer id) {
        DbOneTimeUnavailability dbOneTimeUnavailability = new QDbOneTimeUnavailability()
                .id
                .eq(id)
                .findUnique();

        return dbOneTimeUnavailability;
    }

    /**
     * returns a list of all DbOneTimeUnavailability
     *
     * @return
     */
    public static List<DbOneTimeUnavailability> readAllDbOneTimeUnavailability() {
        List<DbOneTimeUnavailability> dbOneTimeUnavailability = new QDbOneTimeUnavailability()
                .findList();

        return dbOneTimeUnavailability;
    }

    /**
     * returns a list of all DbOneTimeUnavailability by timeStart, timeEnd
     *
     * @param timeStart
     * @param timeEnd
     * @return
     */
//    public static List<DbOneTimeUnavailability> readDbOneTimeUnavailabilityByTimeRange(@Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
//        List<DbOneTimeUnavailability> dbOneTimeUnavailabilityList = DbOneTimeUnavailability.find.query().where()
//                .disjunction()
//                .add(Expr.between(DbOneTimeUnavailability.COLUMN_TIME_START, DbOneTimeUnavailability.COLUMN_TIME_END, timeStart))
//                .add(Expr.between(DbOneTimeUnavailability.COLUMN_TIME_START, DbOneTimeUnavailability.COLUMN_TIME_END, timeEnd))
//                .findList();
//
//        return dbOneTimeUnavailabilityList;
//    }
}
