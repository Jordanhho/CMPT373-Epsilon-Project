package models.databaseModel.helpers;


import models.databaseModel.qualification.DbQualification;
import models.databaseModel.qualification.DbUserQualification;

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

    public static DbUserQualification readDbUserQualificationById(Integer id) {
        return DbUserQualification.find.byId(id);
    }


    public static DbUserQualification readDbUserQualByUserIdAndQualId(Integer userId, Integer qualificationId) {
        DbUserQualification dbUserQualification = DbUserQualification.find
                .query()
                .where()
                .eq(DbUserQualification.COLUMN_USER_ID, userId)
                .and()
                .eq(DbUserQualification.COLUMN_QUALIFICATION_ID, qualificationId)
                .findOne();

        return dbUserQualification;
    }

    public static List<DbQualification> readAllDbQualificationsByUserId(Integer userId){
        List<DbUserQualification> dbUserQualificationList = DbUserQualification.find
                .query()
                .where()
                .eq(DbUserQualification.COLUMN_USER_ID, userId)
                .findList();

        List<DbQualification> dbQualificationList = new ArrayList<>();
        for(DbUserQualification userQualification : dbUserQualificationList){
            dbQualificationList.add(DbQualification.find
                    .byId(userQualification.getQualificationId()));
        }
        return dbQualificationList;
    }

    public static void deleteDbUserQualification(DbUserQualification dbUserQualification) {
        dbUserQualification.delete();
    }

    public static List<DbUserQualification> readAllDbUserQualification() {
        return DbUserQualification.find.all();
    }
}
