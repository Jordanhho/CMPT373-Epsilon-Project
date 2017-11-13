package models.databaseModel.helpers;

import models.databaseModel.qualification.DbQualification;

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
        return DbQualification.find.byId(id);
    }

    public static DbQualification readDbQualificationByName(String name) {
        DbQualification dbQualification = DbQualification.find
                .query()
                .where()
                .eq(DbQualification.COLUMN_QUALIFICATION_NAME, name)
                .findOne();

        return dbQualification;
    }

    public static List<DbQualification> readAllDbQualification() {
        return DbQualification.find.all();
    }
}
