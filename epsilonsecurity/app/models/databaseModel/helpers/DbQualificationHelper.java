package models.databaseModel.helpers;

import models.databaseModel.qualification.DbQualification;
import models.databaseModel.qualification.query.QDbQualification;

import java.util.List;

/**
 * CRUD operations for DbQualification class
 */

public final class DbQualificationHelper {


    private DbQualificationHelper() {

    }

    public static void createDbQualification(DbQualification dbQualification) {
        dbQualification.save();
    }


    public static void deleteDbQualification(DbQualification dbQualification) {
        dbQualification.delete();
    }


    public static DbQualification readDbQualificationById(Integer id) {
        DbQualification dbQualification = new QDbQualification()
                .id
                .eq(id)
                .findUnique();

        return dbQualification;
    }

    public static DbQualification readDbQualificationByName(String name) {
        DbQualification dbQualification = new QDbQualification()
                .qualificationName
                .eq(name)
                .findUnique();

        return dbQualification;
    }

    public static List<DbQualification> readAllDbQualification() {
        List<DbQualification> dbQualificationList = new QDbQualification()
                .findList();

        return dbQualificationList;
    }
}
