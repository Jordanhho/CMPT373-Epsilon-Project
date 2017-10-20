package models.databaseModel.helpers;

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

    public static void createDbOneTimeAvailability(DbOneTimeAvailability dbOneTimeAvailability) {
        dbOneTimeAvailability.save();
    }

    public static void deleteDbOneTimeAvailabilityById(DbOneTimeAvailability dbOneTimeAvailability) {
        dbOneTimeAvailability.delete();
    }

    /**
     * finds a DbOneTimeAvailability by DbOneTimeAvailabilityId
     * @param id
     * @return
     */
    public static DbOneTimeAvailability readDbOneTimeAvailabilityById(Integer id) {
        DbOneTimeAvailability dbOneTimeAvailability = new QDbOneTimeAvailability()
                .id
                .eq(id)
                .findUnique();

        return dbOneTimeAvailability;
    }

    /**
     * returns a list of all DbOneTimeAvailability
     * @return
     */
    public static List<DbOneTimeAvailability> readAllDbOneTimeAvailability() {
        List<DbOneTimeAvailability> dbOneTimeAvailability = new QDbOneTimeAvailability()
                .findList();

        return dbOneTimeAvailability;
    }

    /**
     * returns a list of DbOneTimeAvailability by timeStart, timeEnd
     * @param timeStart
     * @param timeEnd
     * @return
     */
//    public static List<DbOneTimeAvailability> readDbOneTimeAvailabilityByTimeRange(@Nonnull Integer timeStart, @Nonnull Integer timeEnd){
//        List<DbOneTimeAvailability> dbOneTimeAvailabilityList = DbOneTimeAvailability.find.query().where()
//                .disjunction()
//                .add(Expr.between(DbOneTimeAvailability.COLUMN_TIME_START, DbOneTimeAvailability.COLUMN_TIME_END, timeStart))
//                .add(Expr.between(DbOneTimeAvailability.COLUMN_TIME_START, DbOneTimeAvailability.COLUMN_TIME_END, timeEnd))
//                .findList();
//        return dbOneTimeAvailabilityList;
//    }


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
