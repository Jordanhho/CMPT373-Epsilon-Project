package models.databaseModel.helpers;


import models.databaseModel.scheduling.DbShift;
import models.databaseModel.scheduling.query.QDbShift;
import models.databaseModel.scheduling.query.QDbUser;

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
        DbShift dbShift = new QDbShift()
                .shiftTypeId
                .eq(shiftTypeId)
                .findUnique();

        return dbShift;
    }

    public static DbShift readDbShiftByShiftId(Integer shiftId) {
        DbShift dbShift = new QDbShift()
                .id
                .eq(shiftId)
                .findUnique();

        return dbShift;
    }

    /**
     * returns a List just DBshift by timeStart, timeEnd
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public static List<DbShift> readDbShiftByTime(Long timeStart, Long timeEnd){
        List<DbShift> dbShiftList = new QDbShift()
                .timeStart.lessOrEqualTo(timeStart)
                .and()
                .timeEnd.greaterOrEqualTo(timeStart)
                .and()
                .timeStart.lessOrEqualTo(timeStart)
                .and()
                .timeEnd.greaterOrEqualTo(timeEnd)
                .findList();

        return dbShiftList;
    }

    public static void deleteDbShift(DbShift dbShift) {
        dbShift.delete();
    }

    public static void deleteShiftByName(String name) {

    }
    /**
     * returns a list just all DbShift
     * @return
     */
    public static List<DbShift> readAllDbShift() {
        List<DbShift> dbShift = new QDbShift()
                .findList();

        return dbShift;
    }

}
