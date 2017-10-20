package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbShiftName;

import java.util.List;

public class DbShiftNameHelper {

    private DbShiftNameHelper() {

    }

    public static void createDbShiftName(String name, String colorCode) {
        DbShiftName dbShiftName = new DbShiftName(name, colorCode);
        dbShiftName.save();
    }

    public static void deleteDbShiftName(String name) {
        DbShiftName dbShiftName = readDbShiftNameByName(name);
        dbShiftName.delete();
    }

    public static DbShiftName readDbShiftNameByName(String name) {
        DbShiftName dbShiftName = DbShiftName.find
                .query()
                .where()
                .eq(DbShiftName.COLUMN_NAME, name)
                .findOne();
        return dbShiftName;
    }

    public static List<DbShiftName> readAllDbShiftsNameByName(String name) {
        List<DbShiftName> dbShiftNameList = DbShiftName.find
                .query()
                .where()
                .eq(DbShiftName.COLUMN_NAME, name)
                .findList();
        return dbShiftNameList;
    }
}
