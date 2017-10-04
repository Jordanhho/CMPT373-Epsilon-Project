package controllers;

import models.databaseModel.helpers.DbShiftNameHelper;
import models.databaseModel.scheduling.DbShiftName;
import play.mvc.*;


import java.util.Map;

public class ShiftNameController extends Controller {

    public Result createShiftName() {

        final Map<String, String[]> formValues = request().body().asFormUrlEncoded();

        String name = formValues.get(DbShiftName.FORM_NAME)[0];

        DbShiftNameHelper.createDbShiftName(name);

        return ok();
    }

    public Result deleteShiftName() {

        final Map<String, String[]> formValues = request().body().asFormUrlEncoded();

        String shiftName = formValues.get(DbShiftName.FORM_NAME)[0];

        DbShiftName dbShiftName = DbShiftNameHelper.readDbShiftNameByName(shiftName);
        DbShiftNameHelper.deleteDbShiftName(dbShiftName.getName());

        return ok();
    }

}
