package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbShiftType;

import java.util.List;

public class DbShiftTypeHelper {

    private DbShiftTypeHelper() {
    }

    public static void createDbShiftType(DbShiftType dbShiftType) {
        dbShiftType.save();
    }

    public static void deleteDbShiftType(DbShiftType dbShiftType) {
        dbShiftType.delete();
    }

    public static DbShiftType readDbShiftTypeById(Integer id) {
        return DbShiftType.find.byId(id);
    }

    public static List<DbShiftType> readAllDbShiftTypeById(Integer id) {
        List<DbShiftType> dbShiftTypeList = DbShiftType.find
                .query()
                .where()
                .eq(DbShiftType.COLUMN_ID, id)
                .findList();

        return dbShiftTypeList;
    }

    public static DbShiftType readDbShiftTypeByName(String name) {
        DbShiftType dbShiftType = DbShiftType.find
                .query()
                .where()
                .eq(DbShiftType.COLUMN_NAME, name)
                .findUnique();

        return dbShiftType;
    }

    public static DbShiftType readDbShiftTypeByColorCode(String colorCode) {
        DbShiftType dbShiftType = DbShiftType.find
                .query()
                .where()
                .eq(DbShiftType.COLUMN_COLOR_CODE, colorCode)
                .findUnique();

        return dbShiftType;
    }

    public static List<DbShiftType> readAllDbShiftTypes() {
        return DbShiftType.find.all();
    }
}
