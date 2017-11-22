package controllers;

import models.DummyDatabase.DummyDataBase;
import models.databaseModel.helpers.DbOneTimeAvailabilityHelper;
import models.databaseModel.scheduling.DbOneTimeAvailability;
import models.databaseModel.scheduling.Status;
import models.queries.OneTimeAvailabilityArrayForm;
import models.queries.ScheduleUtil;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;


import javax.inject.Inject;
import java.util.List;

public class OneTimeAvailabilityController extends Controller {

    private static final String TIME_START = "start";
    private static final String TIME_END = "end";
    private final FormFactory formFactory;

    @Inject
    OneTimeAvailabilityController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    private DbOneTimeAvailability getDbOneTimeAvailabilityFromForm() {
        Form<DbOneTimeAvailability> form = formFactory.form(DbOneTimeAvailability.class).bindFromRequest();
        return form.get();
    }

    private DbOneTimeAvailability[] getDbOneTimeAvailabilityArrayFromForm() {
<<<<<<< HEAD
        DummyDataBase database = new DummyDataBase();
        Form<OneTimeAvailabilityArrayForm> form = formFactory.form(OneTimeAvailabilityArrayForm.class).bindFromRequest();
=======
        Form<OneTimeAvailabilityArrayForm> form = formFactory
                .form(OneTimeAvailabilityArrayForm.class).bindFromRequest();
>>>>>>> master
        return form.get().getDbOneTimeAvailabilities();
    }

    public Result listOneTimeAvailabilities() {
        List<DbOneTimeAvailability> dbOneTimeAvailabilityList = DbOneTimeAvailabilityHelper
                .readAllDbOneTimeAvailability();
        return ok(Json.toJson(dbOneTimeAvailabilityList));
    }

    public Result createOneTimeAvailability() {
        DbOneTimeAvailability dbOneTimeAvailability = getDbOneTimeAvailabilityFromForm();
        DbOneTimeAvailabilityHelper.createDbOneTimeAvailability(dbOneTimeAvailability);
        return ok();
    }

    public Result createOneTimeAvailabilityFromArray() {
        DbOneTimeAvailability[] dbOneTimeAvailabilities = getDbOneTimeAvailabilityArrayFromForm();
        for (DbOneTimeAvailability dbOneTimeAvailability : dbOneTimeAvailabilities) {
            DbOneTimeAvailabilityHelper.createDbOneTimeAvailability(dbOneTimeAvailability);
        }

        return ok();
    }

    public Result readOneTimeAvailabilityById(Integer id) {
        DbOneTimeAvailability dbOneTimeAvailability = DbOneTimeAvailabilityHelper.readDbOneTimeAvailabilityById(id);
        return ok(Json.toJson(dbOneTimeAvailability));
    }

    public Result deleteOneTimeAvailability(Integer id) {
        DbOneTimeAvailability dbOneTimeAvailability = DbOneTimeAvailabilityHelper.readDbOneTimeAvailabilityById(id);
        DbOneTimeAvailabilityHelper.deleteDbOneTimeAvailability(dbOneTimeAvailability);
        return ok();
    }

    public Result readOneTimeAvailabilitiesByTimeRange() {
        Long timeStart = Long.parseLong(request().getQueryString(TIME_START));
        Long timeEnd = Long.parseLong(request().getQueryString(TIME_END));
        List<DbOneTimeAvailability> dbOneTimeAvailabilityList = DbOneTimeAvailabilityHelper
                .readDbOneTimeAvailabilityByTimeRange(timeStart, timeEnd);

        return ok(Json.toJson(dbOneTimeAvailabilityList));
    }

    public Result readOneTimeAvailabilityStatus(Integer userId, Integer teamId) {
        Long timeStart = Long.parseLong(request().getQueryString(TIME_START));
        Long timeEnd = Long.parseLong(request().getQueryString(TIME_END));
        Status status = ScheduleUtil.getOneTimeAvailStatus(userId, teamId, timeStart, timeEnd);
        return ok(Json.toJson(status));
    }

}
