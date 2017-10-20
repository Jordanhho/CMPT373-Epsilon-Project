package models.databaseModel.helpers;

import io.ebean.Expr;
import models.databaseModel.scheduling.DbOneTimeAvailability;
import models.databaseModel.scheduling.DbUser;
import models.databaseModel.scheduling.query.QDbOneTimeAvailability;

import javax.annotation.Nonnull;
import java.util.ArrayList;
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
    public static void createDbOneTimeAvailability(@Nonnull Integer userTeamId, @Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        DbOneTimeAvailability dbOneTimeAvailability = new DbOneTimeAvailability(userTeamId, timeStart, timeEnd);
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

    /**
     * returns a list of DbOneTimeAvailability by timeStart, timeEnd
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public static List<DbOneTimeAvailability> readDbOneTimeAvailabilityByTimeRange(@Nonnull Integer timeStart, @Nonnull Integer timeEnd){
        return new QDbOneTimeAvailability().
                timeStart.lessOrEqualTo(timeStart).and().
                timeEnd.greaterOrEqualTo(timeStart).and().
                timeStart.lessOrEqualTo(timeStart).and().
                timeEnd.greaterOrEqualTo(timeEnd).findList();
    }

    /**
     * returns a list of all DbOneTimeAvailability
     * @return
     */
    public static List<DbUser> readDbUserByAvailability(@Nonnull List<DbOneTimeAvailability> oneTimeAvailabilityList){
        List<DbUser> dbUserList = new ArrayList<>();
        for(DbOneTimeAvailability oneTimeAvailability : oneTimeAvailabilityList){
            dbUserList.add(DbUser.find.byId(oneTimeAvailability.getUserTeamId()));
        }
        return dbUserList;
    }
}
