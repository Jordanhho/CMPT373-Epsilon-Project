package models.databaseModel.helpers;

import io.ebean.Expr;
import models.databaseModel.scheduling.DbOneTimeAvailability;
import models.databaseModel.scheduling.DbUser;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD operations for DbOneTimeAvailability Class
 */
public final class DbOneTimeAvailabilityHelper {
    private DbOneTimeAvailabilityHelper() {

    }

    public static void createDbOneTimeAvailability(@Nonnull Integer userTeamId, @Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        DbOneTimeAvailability dbOneTimeAvailability = new DbOneTimeAvailability(userTeamId, timeStart, timeEnd);
        dbOneTimeAvailability.save();
    }


    public static void deleteDbOneTimeAvailabilityById(@Nonnull Integer id) {
        DbOneTimeAvailability dbOneTimeAvailability = readDbOneTimeAvailabilityById(id);
        dbOneTimeAvailability.delete();
    }

    public static DbOneTimeAvailability readDbOneTimeAvailabilityById(@Nonnull Integer id) {
        DbOneTimeAvailability dbOneTimeAvailability = DbOneTimeAvailability.find.byId(id);
        return dbOneTimeAvailability;
    }

    public static List<DbOneTimeAvailability> readAllDbOneTimeAvailability() {
        List<DbOneTimeAvailability> dbOneTimeAvailability = DbOneTimeAvailability.find.all();
        return dbOneTimeAvailability;
    }


    public static List<DbOneTimeAvailability> readDbOneTimeAvailabilityByTimeRange(@Nonnull Integer timeStart, @Nonnull Integer timeEnd){
        List<DbOneTimeAvailability> dbOneTimeAvailabilityList = DbOneTimeAvailability.find.query().where()
                .disjunction()
                .add(Expr.ge(DbOneTimeAvailability.COLUMN_TIME_START, timeStart))
                .add(Expr.le(DbOneTimeAvailability.COLUMN_TIME_END, timeEnd))
                .findList();
        return dbOneTimeAvailabilityList;
    }

    public static List<DbUser> readDbUserByAvailability(@Nonnull List<DbOneTimeAvailability> oneTimeAvailabilityList){
        List<DbUser> dbUserList = new ArrayList<>();
        for(DbOneTimeAvailability oneTimeAvailability : oneTimeAvailabilityList){
            dbUserList.add(DbUser.find.byId(oneTimeAvailability.getUserTeamId()));
        }
        return dbUserList;
    }


}
