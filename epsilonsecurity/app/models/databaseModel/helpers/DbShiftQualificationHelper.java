package models.databaseModel.helpers;


import models.databaseModel.qualification.DbQualification;
import models.databaseModel.qualification.DbShiftQualification;
import models.databaseModel.qualification.query.QDbQualification;
import models.databaseModel.qualification.query.QDbShiftQualification;

import java.util.ArrayList;
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

    public static DbShiftQualification readDbShiftQualificationById(Integer shiftTypeId, Integer qualificationId) {
        DbShiftQualification dbShiftQualification = new QDbShiftQualification()
                .shiftTypeId
                .eq(shiftTypeId)
                .and()
                .qualificationId
                .eq(qualificationId)
                .findUnique();

        return dbShiftQualification;
    }

    public static List<DbQualification> readDbQualificationByShiftTypeId(Integer shiftTypeId){
        List<DbShiftQualification> dbShiftQualificationList = new QDbShiftQualification()
                .shiftTypeId
                .eq(shiftTypeId)
                .findList();
        List<DbQualification> dbQualificationList = new ArrayList<>();
        for(DbShiftQualification shiftQualification : dbShiftQualificationList){
            dbQualificationList.add(new QDbQualification()
                .id
                .eq(shiftQualification.getQualificationId())
                .findUnique());
        }
        return dbQualificationList;
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
