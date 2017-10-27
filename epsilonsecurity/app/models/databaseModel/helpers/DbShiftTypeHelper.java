package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbShiftType;
import models.databaseModel.scheduling.query.DbShiftType;

import java.util.List;

/**
 * CRUD operations for DbShiftType class
 */
public final class DbShiftTypeHelper {


    private DbShiftTypeHelper() {

    }


    public static void createDbShiftType(DbShiftType dbShiftType) {
        dbShiftType.save();
    }


    public static void deleteDbShiftType(DbShiftType dbShiftType) {
        dbShiftType.delete();
    }


    public static DbShiftType readDbShiftTypeById(Integer id) {
        DbShiftType dbShiftType = new QDbShiftType()
                .id
                .eq(id)
                .findUnique();

        return dbShiftType;
    }


    public static DbShiftType readDbShiftTypeByName(String name) {
        DbShiftType dbShiftType = new QDbShiftType()
                .name
                .eq(name)
                .findUnique();

        return dbShiftType;
    }

    public static DbShiftType readDbShiftTypeByColorCode(String colorCode) {
        DbShiftType dbShiftType = new QDbShiftType()
                .colorCode
                .eq(colorCode)
                .findUnique();

        return dbShiftType;
    }



    public static List<DbShiftType> readAllDbShiftType() {
        List<DbShiftType> dbShiftTypeList = new QDbShiftType()
                .findList();

        return dbShiftTypeList;
    }
}
