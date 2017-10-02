package controllers;
import com.google.inject.Inject;
import models.databaseModel.scheduling.DbUserShiftForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import models.databaseModel.scheduling.DbUser;
import models.databaseModel.scheduling.DbShift;
import models.databaseModel.scheduling.DbUserShift;
import models.databaseModel.scheduling.DbUserShiftForm;


import java.util.List;

import static models.databaseModel.helpers.DbShiftHelper.readDbShiftByTime;
import static models.databaseModel.helpers.DbUserHelper.readDbUserBySfuEmail;
import static models.databaseModel.helpers.DbUserShiftHelper.createDbUserShift;
import static models.databaseModel.helpers.DbUserShiftHelper.readAllDbUserShift;

public class UserShiftController extends Controller {

    @Inject
    FormFactory formFactory;

    private static int SHIFT_LIST_INDEX = 0;

    public Result listUserShifts() {
        return ok();
    }

    public Result createUserShift() {
        Form<DbUserShiftForm> userShiftForm = formFactory.form(DbUserShiftForm.class).bindFromRequest();
        DbUserShiftForm newUserShift = userShiftForm.get();

        DbUser targetUser = readDbUserBySfuEmail(newUserShift.getSfuEmail());
        DbShift targetShift = readDbShiftByTime(
                newUserShift.getTimeStart(),
                newUserShift.getTimeEnd()).get(SHIFT_LIST_INDEX);

        createDbUserShift(targetUser.getUserId(), targetShift.getId());
        return ok();
    }

    public Result retrieveUserShift() {
        Form<DbUser> userForm = formFactory.form(DbUser.class).bindFromRequest();
        DbUser targetUser = userForm.get();

        List<DbUserShift> dbUserShift = readAllDbUserShift();
        int i = 0;
        while(i < dbUserShift.size()) {
            DbUserShift currentUserShift = dbUserShift.get(i);
            if(!targetUser.getUserId().equals(currentUserShift.getUserId())) {
                dbUserShift.remove(i);
            }
            i++;
        }
        return ok();
    }

    public Result deleteUserShift() {
        return ok();
    }
}