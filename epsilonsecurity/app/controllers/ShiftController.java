package controllers;

import models.databaseModel.helpers.DbShiftHelper;
import models.databaseModel.helpers.DbShiftTypeHelper;
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

    private static final String TIME_START = "start";
    private static final String TIME_END = "end";
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

    //TODO: Refactor deleleShift and all related functions/routes.
    public Result deleteShift(String name) {

        int shiftTypeID = DbShiftTypeHelper.readDbShiftTypeByName(name).getId();
        DbShift dbShiftToDelete = DbShiftHelper.readDbShiftByShiftTypeId(shiftTypeID);
        DbShiftHelper.deleteDbShift(dbShiftToDelete);

        return ok();
    }

    public Result readUsersAvailableForShift(Integer teamId) {
        Long timeStart = Long.parseLong(request().getQueryString(TIME_START));
        Long timeEnd = Long.parseLong(request().getQueryString(TIME_END));
        List<DbUser> dbUserList = ScheduleUtil.queryUsersBasedOnAvailability(teamId, timeStart, timeEnd);

        return ok(Json.toJson(dbUserList));
    }

    public Result readHoursWithShiftTypeByUserId(Integer userId){
        return ok(Json.toJson(ScheduleUtil.getListOfHourWithShiftTypeByUserId(userId)));
    }

    public Result readHoursWorkingByUserId(Integer userId){
        return ok(Json.toJson(ScheduleUtil.getTotalHourWorkingByUserID(userId)));
    }
}