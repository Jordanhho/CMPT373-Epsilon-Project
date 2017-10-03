package models.databaseModel.helpers;



import io.ebean.Expr;
import models.databaseModel.scheduling.DbOneTimeUnavailability;
import models.databaseModel.scheduling.DbUser;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;


/**
 * CRUD operations for DbOneTimeUnavailability Class
 */
public final class DbOneTimeUnavailabilityHelper {

    private DbOneTimeUnavailabilityHelper() {

    }

    public static void createDbOneTimeUnavailability(@Nonnull Integer userTeamId, @Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        DbOneTimeUnavailability dbOneTimeUnavailability = new DbOneTimeUnavailability(userTeamId, timeStart, timeEnd);
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

    public static List<DbOneTimeUnavailability> readDbOneTimeUnavailabilityByTimeRange(@Nonnull Integer timeStart, @Nonnull Integer timeEnd){
        List<DbOneTimeUnavailability> dbOneTimeUnavailabilityList = DbOneTimeUnavailability.find.query().where()
                .disjunction()
                .add(Expr.between(DbOneTimeUnavailability.COLUMN_TIME_START, DbOneTimeUnavailability.COLUMN_TIME_END, timeStart))
                .add(Expr.between(DbOneTimeUnavailability.COLUMN_TIME_START, DbOneTimeUnavailability.COLUMN_TIME_END, timeEnd))
                .findList();
        return dbOneTimeUnavailabilityList;
    }


}
