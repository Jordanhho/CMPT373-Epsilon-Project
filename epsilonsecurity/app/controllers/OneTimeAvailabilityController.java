package controllers;

import play.mvc.*;
import models.databaseModel.scheduling.DbOneTimeAvailability;
import models.databaseModel.helpers.DbOneTimeAvailabilityHelper;

public class OneTimeAvailabilityController extends Controller {

//    TODO: Implement functionality just OneTimeAvailabilityController
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
}
