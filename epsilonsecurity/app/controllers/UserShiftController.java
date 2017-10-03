package controllers;

import com.google.inject.Inject;
import models.databaseModel.helpers.DbUserHelper;
import models.databaseModel.helpers.DbUserShiftHelper;
import models.databaseModel.scheduling.DbUserShiftForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import models.databaseModel.scheduling.DbUser;
import models.databaseModel.scheduling.DbShift;
import models.databaseModel.scheduling.DbUserShift;
import models.databaseModel.scheduling.DbUserShiftForm;


import java.util.List;
import java.util.Map;

import static models.databaseModel.helpers.DbShiftHelper.readDbShiftByTime;
import static models.databaseModel.helpers.DbUserHelper.readDbUserBySfuEmail;
import static models.databaseModel.helpers.DbUserShiftHelper.createDbUserShift;
import static models.databaseModel.helpers.DbUserShiftHelper.readAllDbUserShift;

public class UserShiftController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result createUserShift() {
        final Map<String, String[]> values = request().body().asFormUrlEncoded();

        Integer userId = Integer.parseInt(values.get("userId")[0]);
        Integer shiftId = Integer.parseInt(values.get("shiftId")[0]);

        DbUserShiftHelper.createDbUserShift(userId, shiftId);
        return ok();
    }

    public Result retrieveUserShift(Integer userId) {
        List<DbUserShift> dbUserShiftList = DbUserShiftHelper.readDbUserByShiftId(userId);

        for (DbUserShift dbUserShift : dbUserShiftList) {
            System.out.println(dbUserShift.getUserId());
        }

        return ok();
    }

    public Result deleteUserShift(Integer userId) {
        return ok();
    }
}