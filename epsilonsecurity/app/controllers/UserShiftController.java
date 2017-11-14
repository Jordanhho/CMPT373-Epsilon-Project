package controllers;

import email.MailerServiceCron;
import models.databaseModel.helpers.DbUserShiftHelper;
import models.databaseModel.scheduling.DbUserShift;
import models.queries.ScheduleUtil;
import models.queries.ShiftWithCampus;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class UserShiftController extends Controller {

    private final FormFactory formFactory;
    private final MailerServiceCron mailerServiceCron;

    @Inject
    UserShiftController(FormFactory formFactory, MailerServiceCron mailerServiceCron) {
        this.formFactory = formFactory;
        this.mailerServiceCron = mailerServiceCron;
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

    public Result createUserShift(Integer userId, Integer shiftId) {
        // TODO: Implement createUserShift() with input parameters
        return ok();
    }

    public Result retrieveUserShift(Integer userId) {
        List<DbUserShift> dbUserShiftList = DbUserShiftHelper.readAllDbUserShiftByUserId(userId);

        return ok(Json.toJson(dbUserShiftList));
    }

    public Result retrieveShiftsByUserId(Integer userId) {
        List<ShiftWithCampus> shiftsWithCampusList = ScheduleUtil.getShiftsWithCampusByUserId(userId);
        return ok(Json.toJson(shiftsWithCampusList));
    }

    public Result deleteUserShifts(Integer shiftId) {
        List<DbUserShift> dbUserShiftsToDelete = DbUserShiftHelper
                .readDbUserShiftByShiftId(shiftId);

        DbUserShiftHelper.deleteDbUserShifts(dbUserShiftsToDelete);
        return ok();
    }
}