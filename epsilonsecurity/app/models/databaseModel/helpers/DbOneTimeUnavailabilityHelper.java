package models.databaseModel.helpers;


import io.ebean.Expr;
import models.databaseModel.scheduling.DbOneTimeUnavailability;

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

    public static DbOneTimeUnavailability readDbOneTimeUnavailabilityById(Integer id) {
        return DbOneTimeUnavailability.find.byId(id);
    }

    public static List<DbOneTimeUnavailability> readAllDbOneTimeUnavailability() {
        return DbOneTimeUnavailability.find.all();
    }

    public static List<DbOneTimeUnavailability> readDbOneTimeUnavailabilityByTimeRange(Long timeStart, Long timeEnd) {
        List<DbOneTimeUnavailability> dbOneTimeUnavailabilityList = DbOneTimeUnavailability.find
                .query()
                .where()
                .disjunction()
                .add(Expr.between
                        (DbOneTimeUnavailability.COLUMN_TIME_START, DbOneTimeUnavailability.COLUMN_TIME_END, timeStart))
                .add(Expr.between
                        (DbOneTimeUnavailability.COLUMN_TIME_START, DbOneTimeUnavailability.COLUMN_TIME_END, timeEnd))
                .findList();


        return dbOneTimeUnavailabilityList;
    }
}
