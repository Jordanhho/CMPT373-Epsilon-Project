package models.databaseModel.helpers;


import models.databaseModel.scheduling.DbShift;
import models.databaseModel.scheduling.query.QDbShift;

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

    public static DbShift readDbShiftByName(String shiftName) {
        DbShift dbShift = new QDbShift()
                .name
                .eq(shiftName)
                .findUnique();

        return dbShift;
    }

//    /**
//     * returns a List of DBshift by timeStart, timeEnd
//     * @param timeStart
//     * @param timeEnd
//     * @return
//     */
//    public static List<DbShift> readDbShiftByTime(Integer timeStart, Integer timeEnd){
//        List<DbShift> dbShiftList = DbShift.find.query().where()
//                .disjunction()
//                .add(Expr.between(DbShift.COLUMN_TIME_START, DbShift.COLUMN_TIME_END, timeStart))
//                .add(Expr.between(DbShift.COLUMN_TIME_START, DbShift.COLUMN_TIME_END, timeEnd))
//                .findList();
//        return dbShiftList;
//    }

    public static void deleteDbShiftByName(DbShift dbShift) {
        dbShift.delete();
    }

    /**
     * returns a list of all DbShift
     * @return
     */
    public static List<DbShift> readAllDbShift() {
        List<DbShift> dbShift = DbShift.find.all();
        return dbShift;
    }

}
