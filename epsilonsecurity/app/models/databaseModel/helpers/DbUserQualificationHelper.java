package models.databaseModel.helpers;


import models.databaseModel.scheduling.DbUserQualification;
import models.databaseModel.scheduling.query.QDbUserQualification;

import java.util.List;


/**
 * CRUD operations for DbUserQualification class
 */
public final class DbUserQualificationHelper {

    private DbUserQualificationHelper() {

    }

    public static void createDbUserQualification(DbUserQualification dbUserQualification) {
        dbUserQualification.save();
    }


    public static DbUserQualification readDbUserQualByUserIdAndQualId(Integer userId, Integer qualificationId) {
        DbUserQualification dbUserQualification = new QDbUserQualification()
                .userId
                .eq(userId)
                .and()
                .qualificationId
                .eq(qualificationId)
                .findUnique();

        return dbUserQualification;
    }



    public static void deleteDbUserQualification(DbUserQualification dbUserQualification) {
        dbUserQualification.delete();
    }

    /**
     * returns a list of all DbUserQualification
     * @return
     */
    public static List<DbUserQualification> readAllDbUserQualification() {
        List<DbUserQualification> dbUserQualificationList = new QDbUserQualification()
                .findList();

        return dbUserQualificationList;
    }

}
