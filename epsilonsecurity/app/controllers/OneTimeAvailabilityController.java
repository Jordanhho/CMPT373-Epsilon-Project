package controllers;

import play.mvc.*;
import models.databaseModel.scheduling.DbOneTimeAvailability;
import models.databaseModel.helpers.DbOneTimeAvailabilityHelper;

public class OneTimeAvailabilityController extends Controller {

//    TODO: Implement functionality of OneTimeAvailabilityController
    public Result listOneTimeAvailability() {
        return ok();
    }

    public Result createOneTimeAvailability() {
        return ok();
    }

    public Result retrieveOneTimeAvailability(Integer oneTimeAvailabilityId) {
        return ok();
    }

    public Result deleteOneTimeAvailability() {
        return ok();
    }

    public Result readOneTimeAvailabilitiesByTimeRange(Integer timeStart, Integer timeEnd) {
        return ok();
    }
}
