package controllers;
import models.databaseModel.scheduling.DbShift;
import models.databaseModel.scheduling.DbUser;
import models.queries.ScheduleUtil;
import play.mvc.*;

import models.databaseModel.helpers.DbShiftHelper;

import java.util.List;
import java.util.Map;

public class ShiftController extends Controller {

    public Result listShifts() {
        return ok();
    }

    public Result createShift() {
        final Map<String, String[]> values = request().body().asFormUrlEncoded();

        String name = values.get(DbShift.FORM_COLUMN_NAME)[0];
        Integer timeStart = Integer.parseInt(values.get(DbShift.FORM_COLUMN_TIME_START)[0]);
        Integer timeEnd = Integer.parseInt(values.get(DbShift.FORM_COLUMN_TIME_END)[0]);

        DbShiftHelper.createDbShift(name, timeStart, timeEnd);

        return ok();
    }

    public Result retrieveShift() {
        return ok();
    }

    public Result deleteShift() {
        final Map<String, String[]> values = request().body().asFormUrlEncoded();

        String shiftName = values.get(DbShift.FORM_COLUMN_NAME)[0];
        DbShiftHelper.deleteDbShiftByName(shiftName);

        return ok();
    }

    public Result readUsersAvailableForShift(Integer teamId, Integer timeStart, Integer timeEnd) {
        List<DbUser> dbUserList = ScheduleUtil.queryUsersBasedOnAvailiability(teamId, timeStart, timeEnd);


        for (DbUser dbUser : dbUserList) {
            System.out.println(dbUser.getSfuEmail());
        }

        return ok();
    }
}