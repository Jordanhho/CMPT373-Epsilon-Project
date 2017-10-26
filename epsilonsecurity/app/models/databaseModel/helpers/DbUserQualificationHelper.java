package models.databaseModel.helpers;

import models.databaseModel.qualification.DbUserQualification;

import java.util.List;

public class DbUserQualificationHelper {
    private DbUserQualificationHelper() {
    }

    public static void createUserQualification(int qualificationId, int shiftNameId){
        DbUserQualification userQualification = new DbUserQualification(qualificationId, shiftNameId);
        userQualification.save();
    }

    public static DbUserQualification readUserQualificationById(int id) {
        return DbUserQualification.find.byId(id);
    }

    public static void deleteUserQualificationById(int id){
        DbUserQualification dbUserQualification = readUserQualificationById(id);
        dbUserQualification.delete();
    }

    public static List<DbUserQualification> readAllQualification(){
        return DbUserQualification.find.all();
    }
}
