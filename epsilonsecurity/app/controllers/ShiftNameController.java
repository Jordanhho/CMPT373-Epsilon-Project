package controllers;

import models.databaseModel.helpers.DbShiftNameHelper;
import models.databaseModel.scheduling.DbShiftName;
import play.mvc.*;


import java.util.Map;

public class ShiftNameController extends Controller {

    public Result createShiftName() {
        final Map<String, String[]> values = request().body().asFormUrlEncoded();

        String name = values.get("name")[0];

        DbShiftNameHelper.createDbShiftName(name);

        return ok();
    }

    public Result deleteShiftName(String shiftName) {
        DbShiftName dbShiftName = DbShiftNameHelper.readDbShiftNameByName(shiftName);
        DbShiftNameHelper.deleteDbShiftName(dbShiftName.getName());

        return ok();
    }

}
