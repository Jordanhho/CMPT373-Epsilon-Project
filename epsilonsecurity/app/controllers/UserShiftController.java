package controllers;

import models.databaseModel.helpers.DbUserShiftHelper;
import models.databaseModel.scheduling.DbUserShift;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class UserShiftController extends Controller {

    private final FormFactory formFactory;

    @Inject
    UserShiftController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    private DbUserShift getDbUserShiftFromForm() {

        // From the request, create a form that can handle a DbUserShift object.
        Form<DbUserShift> form = formFactory.form(DbUserShift.class).bindFromRequest();

        // Create a DbUserShift object from the form data.
        DbUserShift dbUserShift = form.get();

        return dbUserShift;
    }

    public Result createUserShift() {

        // Create a DbUserShift object from the form data.
        DbUserShift dbUserShift = getDbUserShiftFromForm();

        // Enter the DbUserShift into the database.
        DbUserShiftHelper.createDbUserShift(dbUserShift);

        return ok();
    }

    public Result retrieveUserShift(Integer userId) {
        List<DbUserShift> dbUserShiftList = DbUserShiftHelper.readDbUserByShiftId(userId);

        return ok(Json.toJson(dbUserShiftList));
    }

    public Result deleteUserShifts() {

        // Create a DbUserShift object from the form data.
        DbUserShift dbUserShift = getDbUserShiftFromForm();

        // Read the DbUserShift to delete based on the form fields
        List<DbUserShift> dbUserShiftsToDelete = DbUserShiftHelper
                .readDbUserShiftByShiftId(dbUserShift.getShiftId());


        DbUserShiftHelper.deleteDbUserShifts(dbUserShiftsToDelete);

        return ok();
    }
}