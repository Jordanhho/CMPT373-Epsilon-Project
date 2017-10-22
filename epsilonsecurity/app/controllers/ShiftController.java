package controllers;

import models.databaseModel.helpers.DbShiftHelper;
import models.databaseModel.scheduling.DbShift;
import models.databaseModel.scheduling.DbUser;
import models.queries.ScheduleUtil;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class ShiftController extends Controller {

    private final FormFactory formFactory;

    @Inject
    ShiftController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    private DbShift getDbShiftFromForm() {

        // From the request, create a form that can handle a DbShift object.
        Form<DbShift> form = formFactory.form(DbShift.class).bindFromRequest();

        // Create a DbShift object from the form data.
        DbShift dbShift = form.get();

        return dbShift;
    }

    public Result createShift() {

        // Create a DbShift object from the form data
        DbShift dbShift = getDbShiftFromForm();

        // Enter the DbTeam into the database.
        DbShiftHelper.createDbShift(dbShift);

        return ok();
    }

    public Result readShifts() {
        return ok();
    }

    public Result deleteShift() {

        // Create a DbShift object form the form data.
        DbShift dbShift = getDbShiftFromForm();

        // Read the DbShift to delete based on the form fields
        DbShift dbShiftToDelete = DbShiftHelper.readDbShiftByName(dbShift.getName());

        DbShiftHelper.deleteDbShiftByName(dbShiftToDelete);

        return ok();
    }

    public Result readUsersAvailableForShift(Integer teamId, Integer timeStart, Integer timeEnd) {
        List<DbUser> dbUserList = ScheduleUtil.queryUsersBasedOnAvailability(teamId, timeStart, timeEnd);

        return ok(Json.toJson(dbUserList));
    }
}