package controllers;

import models.databaseModel.scheduling.Status;
import models.queries.ScheduleUtil;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class OneTimeAvailabilityController extends Controller {

    private static String TIME_START = "start";
    private static String TIME_END = "end";

    public Result listOneTimeAvailabilities() {
        return ok();
    }

    public Result createOneTimeAvailability() {
        return ok();
    }

    public Result retrieveOneTimeAvailability(Integer oneTimeAvailabilityId) {
        return ok();
    }

    public Result deleteOneTimeAvailability(Integer oneTimeAvailabilityId) {
        return ok();
    }

    public Result readOneTimeAvailabilitiesByTimeRange() {
        return ok();
    }

    public Result readOneTimeAvailabilityStatus(Integer userId, Integer teamId) {
        Long timeStart = Long.parseLong(request().getQueryString(TIME_START));
        Long timeEnd = Long.parseLong(request().getQueryString(TIME_END));
        Status status = ScheduleUtil.getOneTimeAvailStatus(userId, teamId, timeStart, timeEnd);
        return ok(Json.toJson(status));
    }

}
