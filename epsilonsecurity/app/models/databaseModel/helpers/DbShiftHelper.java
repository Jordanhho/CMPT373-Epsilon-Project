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

    public static void createDbShift(@Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        DbShift dbShift = new DbShift(timeStart, timeEnd);
        dbShift.save();
    }

    public static DbShift readDbShiftById(@Nonnull Integer id) {
        DbShift dbShift = DbShift.find.byId(id);
        return dbShift;
    }

    public static List<DbShift> readDbShiftByTime(Integer startTime, Integer endTime){
        List<DbShift> dbShiftList = DbShift.find.query().where()
                .disjunction()
                .add(Expr.eq("time_start", startTime))
                .add(Expr.eq("time_end", endTime))
                .findList();
        return dbShiftList;
    }

    public static void deleteDbShiftById(@Nonnull Integer id) {
        DbShift dbShift = readDbShiftById(id);
        dbShift.delete();
    }

    public static List<DbShift> readAllDbShift() {
        List<DbShift> dbShift = DbShift.find.all();
        return dbShift;
    }
}
