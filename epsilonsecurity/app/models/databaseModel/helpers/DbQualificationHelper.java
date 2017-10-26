package models.databaseModel.helpers;

import models.databaseModel.qualification.DbQualification;
import models.databaseModel.qualification.query.QDbQualification;

import java.util.List;

public final class DbQualificationHelper {
    private DbQualificationHelper() {
    }

    public static void createQualification(String qualificationName){
        DbQualification dbQualification = new DbQualification(qualificationName);
        dbQualification.save();
    }

    public static DbQualification readQualificationById(int id) {
        DbQualification dbQualification = DbQualification.find.byId(id);
        return dbQualification;
    }

    public static DbQualification readQualificationByName(String qualificationName){
        return new QDbQualification().qualificationName.eq(qualificationName).findUnique();
    }

    public static void deleteQualificationById(int id){
        DbQualification dbQualification = readQualificationById(id);
        dbQualification.delete();
    }

    public static List<DbQualification> readAllQualification(){
        return DbQualification.find.all();
    }



}
