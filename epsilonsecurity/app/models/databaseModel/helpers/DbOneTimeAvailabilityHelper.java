package models.databaseModel.helpers;

import io.ebean.Expr;
import models.databaseModel.scheduling.DbOneTimeAvailability;

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

    public static DbOneTimeAvailability readDbOneTimeAvailabilityById(Integer id) {
        return DbOneTimeAvailability.find.byId(id);
    }

<<<<<<< HEAD
    /**
     * returns a list just all DbOneTimeAvailability
     *
     * @return
     */
=======
>>>>>>> master
    public static List<DbOneTimeAvailability> readAllDbOneTimeAvailability() {
        return DbOneTimeAvailability.find.all();
    }

<<<<<<< HEAD
    /**
     * returns a list just DbOneTimeAvailability by timeStart, timeEnd
     *
     * @param timeStart
     * @param timeEnd
     * @return
     */
=======
>>>>>>> master
    public static List<DbOneTimeAvailability> readDbOneTimeAvailabilityByTimeRange(Long timeStart, Long timeEnd) {
        List<DbOneTimeAvailability> dbOneTimeAvailabilityList = DbOneTimeAvailability.find
                .query()
                .where()
                .disjunction()
                .add(Expr.between
                        (DbOneTimeAvailability.COLUMN_TIME_START, DbOneTimeAvailability.COLUMN_TIME_END, timeStart))
                .add(Expr.between
                        (DbOneTimeAvailability.COLUMN_TIME_START, DbOneTimeAvailability.COLUMN_TIME_END, timeEnd))
                .findList();

        return dbOneTimeAvailabilityList;
    }
<<<<<<< HEAD

    /**
     * returns a list just all DbOneTimeAvailability
     *
     * @return
     */
    public static List<DbUser> readDbUserByAvailability(List<DbOneTimeAvailability> oneTimeAvailabilityList) {
        List<DbUser> dbUserList = new ArrayList<>();

        for (DbOneTimeAvailability oneTimeAvailability : oneTimeAvailabilityList) {
            dbUserList.add(new QDbUser()
                    .id
                    .eq(oneTimeAvailability.getUserTeamId())
                    .findUnique());
        }

        return dbUserList;
    }
=======
>>>>>>> master
}
