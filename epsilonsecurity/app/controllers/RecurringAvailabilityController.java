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

    public Result deleteRecurringAvailability() {
        return ok();
    }

    public Result readRecurringAvailabilitiesByTimeRange(Integer timeStart, Integer timeEnd) {
        return ok();
    }
}
