package models.databaseModel.helpers;


import models.databaseModel.qualification.DbQualification;
import models.databaseModel.qualification.DbShiftQualification;

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


    public static DbShiftQualification readDbShiftQualByShiftTypeIdAndQualId(Integer shiftTypeId, Integer qualificationId) {
        DbShiftQualification dbShiftQualification = DbShiftQualification.find
                .query()
                .where()
                .eq(DbShiftQualification.COLUMN_SHIFT_TYPE_ID, shiftTypeId)
                .and()
                .eq(DbShiftQualification.COLUMN_QUALIFICATION_ID, qualificationId)
                .findUnique();

        return dbShiftQualification;
    }

    public static List<DbQualification> readDbQualificationByShiftTypeId(Integer shiftTypeId){
        List<DbShiftQualification> dbShiftQualificationList = DbShiftQualification.find
                .query()
                .where()
                .eq(DbShiftQualification.COLUMN_SHIFT_TYPE_ID, shiftTypeId)
                .findList();
        List<DbQualification> dbQualificationList = new ArrayList<>();
        for(DbShiftQualification shiftQualification : dbShiftQualificationList){
            dbQualificationList.add(DbQualification.find
                    .byId(shiftQualification.getQualificationId()));
        }
        return dbQualificationList;
    }

    public static void deleteDbShiftQualification(DbShiftQualification dbShiftQualification) {
        dbShiftQualification.delete();
    }

    public static List<DbShiftQualification> readAllDbShiftQualification() {
        return DbShiftQualification.find.all();
    }

}
