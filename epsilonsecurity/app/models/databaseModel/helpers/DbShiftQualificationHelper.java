package models.databaseModel.helpers;

import models.databaseModel.qualification.DbShiftQualification;
import models.databaseModel.scheduling.DbShiftName;
import models.databaseModel.scheduling.query.QDbShiftName;

import java.util.List;

public class DbShiftQualificationHelper {
    private DbShiftQualificationHelper() {
    }

    public static void createShiftQualification(int qualificationId, int shiftNameId){
        DbShiftQualification shiftQualification = new DbShiftQualification(qualificationId, shiftNameId);
        shiftQualification.save();
    }

    public static DbShiftQualification readShiftQualificationById(int id) {
        return DbShiftQualification.find.byId(id);
    }

    public static void deleteShiftQualificationById(int id){
        DbShiftQualification dbShiftQualification = readShiftQualificationById(id);
        dbShiftQualification.delete();
    }

    public static List<DbShiftQualification> readAllQualification(){
        return DbShiftQualification.find.all();
    }
}
