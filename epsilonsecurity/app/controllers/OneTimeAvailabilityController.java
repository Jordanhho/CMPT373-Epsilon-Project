package controllers;

import models.databaseModel.helpers.DbOneTimeAvailabilityHelper;
import models.databaseModel.scheduling.DbOneTimeAvailability;
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


    public Result listOneTimeAvailabilities() {
        List<DbOneTimeAvailability> dbOneTimeAvailabilityList = DbOneTimeAvailabilityHelper.readAllDbOneTimeAvailability();
        return ok(Json.toJson(dbOneTimeAvailabilityList));
    }

    public Result createOneTimeAvailability() {
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
        List<DbOneTimeAvailability> dbOneTimeAvailabilityList =
                DbOneTimeAvailabilityHelper.readDbOneTimeAvailabilityByTimeRange(timeStart, timeEnd);

        return ok(Json.toJson(dbOneTimeAvailabilityList));
    }
}
