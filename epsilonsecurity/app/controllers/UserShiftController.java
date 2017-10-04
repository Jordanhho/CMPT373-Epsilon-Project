package controllers;

import models.databaseModel.helpers.DbUserShiftHelper;
import models.databaseModel.scheduling.DbUserShift;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import java.util.Map;

public class UserShiftController extends Controller {

    public Result createUserShift() {

        final Map<String, String[]> formValues = request().body().asFormUrlEncoded();

        Integer userId = Integer.parseInt(
                formValues.get(DbUserShift.FORM_USER_ID)[0]);
        Integer shiftId = Integer.parseInt(
                formValues.get(DbUserShift.FORM_SHIFT_ID)[0]);

        DbUserShiftHelper.createDbUserShift(userId, shiftId);

        return ok();
    }

    public Result retrieveUserShift(Integer userId) {
        List<DbUserShift> dbUserShiftList = DbUserShiftHelper.readDbUserByShiftId(userId);

        for (DbUserShift dbUserShift : dbUserShiftList) {
            System.out.println(dbUserShift.getUserTeamId());
        }

        return ok();
    }

    public Result deleteUserShift() {

        final Map<String, String[]> formValues = request().body().asFormUrlEncoded();

        Integer userId = Integer.parseInt(
                formValues.get(DbUserShift.FORM_USER_ID)[0]);
        Integer shiftId = Integer.parseInt(
                formValues.get(DbUserShift.FORM_SHIFT_ID)[0]);

        DbUserShiftHelper.deleteDbUserShiftByUserIdAndShiftId(userId, shiftId);

        return ok();
    }
}