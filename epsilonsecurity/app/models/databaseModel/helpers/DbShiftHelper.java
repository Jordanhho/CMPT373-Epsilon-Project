package models.databaseModel.helpers;


import io.ebean.Expr;
import models.databaseModel.scheduling.DbShift;

import java.util.List;


/**
 * CRUD operations for DbShift class
 */
public final class DbShiftHelper {

    private DbShiftHelper() {

    }

    public static void createDbShift(DbShift dbShift) {
        dbShift.save();
    }

    //This is more likely to return a list
    public static DbShift readDbShiftByShiftTypeId(Integer shiftTypeId) {
        DbShift dbShift = DbShift.find
                .query()
                .where()
                .eq(DbShift.COLUMN_SHIFT_TYPE_ID, shiftTypeId)
                .findUnique();

        return dbShift;
    }

    public static DbShift readDbShiftById(Integer id) {
        return DbShift.find.byId(id);
    }

    public static List<DbShift> readDbShiftByTime(Long timeStart, Long timeEnd){
        List<DbShift> dbShiftList = DbShift.find
                .query()
                .where()
                .disjunction()
                .add(Expr.between(DbShift.COLUMN_TIME_START, DbShift.COLUMN_TIME_END, timeStart))
                .add(Expr.between(DbShift.COLUMN_TIME_START, DbShift.COLUMN_TIME_END, timeEnd))
                .findList();

        return dbShiftList;
    }

    public static void deleteDbShift(DbShift dbShift) {
        dbShift.delete();
    }

    public static void deleteShiftByName(String name) {

    }

    public static List<DbShift> readAllDbShift() {
        return DbShift.find.all();
    }

}
