package models.databaseModel.helpers;


import io.ebean.Expr;
import models.databaseModel.scheduling.DbShift;

import javax.annotation.Nonnull;
import java.util.List;


/**
 * CRUD operations for DbShift class
 */
public final class DbShiftHelper {

    private DbShiftHelper() {
    }

    /**
     * creates a DbShift from name, timeStart, timeEnd
     * @param name
     * @param timeStart
     * @param timeEnd
     */
    public static void createDbShift(@Nonnull String name, @Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        DbShift dbShift = new DbShift(name, timeStart, timeEnd);
        dbShift.save();
    }

    /**
     * finds a DbShift by DbShiftId
     * @param id
     * @return
     */
    public static DbShift readDbShiftById(@Nonnull Integer id) {
        DbShift dbShift = DbShift.find.byId(id);
        return dbShift;
    }

    /**
     * returns a List of DBshift by timeStart, timeEnd
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public static List<DbShift> readDbShiftByTime(Integer timeStart, Integer timeEnd){
        List<DbShift> dbShiftList = DbShift.find.query().where()
                .disjunction()
                .add(Expr.between(DbShift.COLUMN_TIME_START, DbShift.COLUMN_TIME_END, timeStart))
                .add(Expr.between(DbShift.COLUMN_TIME_START, DbShift.COLUMN_TIME_END, timeEnd))
                .findList();
        return dbShiftList;
    }

    /**
     * deletes a DbShift by DbShiftId
     * @param id
     */
    public static void deleteDbShiftById(@Nonnull Integer id) {
        DbShift dbShift = readDbShiftById(id);
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
