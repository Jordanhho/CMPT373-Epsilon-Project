package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbShiftName;
import models.databaseModel.scheduling.query.QDbShiftName;

import java.util.List;

public class DbShiftNameHelper {

    private DbShiftNameHelper() {

    }

    public static void createDbShiftName(DbShiftName dbShiftName) {
        dbShiftName.save();
    }

    public static void deleteDbShiftName(DbShiftName dbShiftName) {
        dbShiftName.delete();
    }

    public static DbShiftName readDbShiftNameByName(String name) {
        DbShiftName dbShiftName = new QDbShiftName()
                .name
                .eq(name)
                .findUnique();

        return dbShiftName;
    }

    public static List<DbShiftName> readAllDbShiftsNameByName(String name) {
        List<DbShiftName> dbShiftNameList = new QDbShiftName()
                .name
                .eq(name)
                .findList();

        return dbShiftNameList;
    }
}
