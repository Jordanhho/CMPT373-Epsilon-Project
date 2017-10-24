package controllers;

import play.mvc.*;
import models.databaseModel.scheduling.DbRecurringAvailability;
import models.databaseModel.helpers.DbRecurringAvailabilityHelper;

public class RecurringAvailabilityController extends Controller {

//    TODO: Implement functionality of RecurringAvailabilityController
    public Result listRecurringAvailabilities() {
        return ok();
    }

    public Result createRecurringAvailability() {
        return ok();
    }

    public Result retrieveRecurringAvailability(Integer recurringAvailabilityId) {
        return ok();
    }

    public Result deleteRecurringAvailability(Integer recurringAvailabilityId) {
        return ok();
    }

    public Result readRecurringAvailabilitiesByTimeRange() {
        return ok();
    }
}
