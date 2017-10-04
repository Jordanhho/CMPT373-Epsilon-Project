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


    /**
     * creates a DbOneTimeUnavailability from userId, timeStart, timeEnd
     * @param userTeamId
     * @param timeStart
     * @param timeEnd
     */
    public static void createDbOneTimeUnavailability(@Nonnull Integer userTeamId, @Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        DbOneTimeUnavailability dbOneTimeUnavailability = new DbOneTimeUnavailability(userTeamId, timeStart, timeEnd);

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

    /**
     * returns a list of all DbOneTimeUnavailability by timeStart, timeEnd
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public static List<DbOneTimeUnavailability> readDbOneTimeUnavailabilityByTimeRange(@Nonnull Integer timeStart, @Nonnull Integer timeEnd){
        List<DbOneTimeUnavailability> dbOneTimeUnavailabilityList = DbOneTimeUnavailability.find.query().where()
                .disjunction()
                .add(Expr.between(DbOneTimeUnavailability.COLUMN_TIME_START, DbOneTimeUnavailability.COLUMN_TIME_END, timeStart))
                .add(Expr.between(DbOneTimeUnavailability.COLUMN_TIME_START, DbOneTimeUnavailability.COLUMN_TIME_END, timeEnd))
                .findList();
        return dbOneTimeUnavailabilityList;
    }


}
