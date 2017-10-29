package models.databaseModel.helpers;


import models.databaseModel.scheduling.DbShiftQualification;
import models.databaseModel.scheduling.query.QDbShiftQualification;

import java.util.List;


/**
 * CRUD operations for DbShiftQualification class
 */
public final class DbShiftQualificationHelper {

    private DbShiftQualificationHelper() {

    }

    public static void createDbShiftQualification(DbShiftQualification dbShiftQualification) {
        dbShiftQualification.save();
    }


    public static DbShiftQualification readDbShiftQualByShiftTypeIdAndQualId(Integer shiftTypeId, Integer qualificationId) {
        DbShiftQualification dbShiftQualification = new QDbShiftQualification()
                .shiftTypeId
                .eq(shiftTypeId)
                .and()
                .qualificationId
                .eq(qualificationId)
                .findUnique();

        return dbShiftQualification;
    }

    public static void deleteDbShiftQualification(DbShiftQualification dbShiftQualification) {
        dbShiftQualification.delete();
    }

    /**
     * returns a list of all DbShiftQualification
     */
    public static List<DbShiftQualification> readAllDbShiftQualification() {
        List<DbShiftQualification> dbShiftQualificationList = new QDbShiftQualification()
                .findList();

        return dbShiftQualificationList;
    }

}
