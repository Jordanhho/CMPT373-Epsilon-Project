package models.databaseModel.helpers;


import models.databaseModel.qualification.DbQualification;
import models.databaseModel.qualification.query.QDbQualification;
import models.databaseModel.qualification.DbUserQualification;
import models.databaseModel.qualification.query.QDbUserQualification;

import java.util.ArrayList;
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

    public static DbUserQualification readDbUserQualificationById(Integer userId, Integer qualificationId) {
        DbUserQualification dbUserQualification = new QDbUserQualification()
                .userId
                .eq(userId)
                .and()
                .qualificationId
                .eq(qualificationId)
                .findUnique();

        return dbUserQualification;
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

    public static List<DbQualification> readDbQualificationByUserId(Integer userId){
        List<DbUserQualification> dbUserQualificationList = new QDbUserQualification()
                .userId
                .eq(userId)
                .findList();
        List<DbQualification> dbQualificationList = new ArrayList<>();
        for(DbUserQualification userQualification : dbUserQualificationList){
            dbQualificationList.add(new QDbQualification()
                .id
                .eq(userQualification.getQualificationId())
                .findUnique());
        }
        return dbQualificationList;
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
