package controllers;

import models.databaseModel.helpers.DbShiftTypeHelper;
import models.databaseModel.scheduling.DbShiftType;
import models.queries.ScheduleUtil;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class ShiftTypeController extends Controller {

    private final FormFactory formFactory;

    @Inject
    ShiftTypeController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    private DbShiftType getDbShiftTypeFromForm() {

        // From the request, create a form that can handle a DbShiftType object
        Form<DbShiftType> form = formFactory.form(DbShiftType.class).bindFromRequest();

        // Create a DbShiftType object from the form data.
        DbShiftType dbShiftType = form.get();

        return dbShiftType;
    }

    public Result createShiftType() {

        DbShiftType dbShiftType = getDbShiftTypeFromForm();

        // Enter the DbShiftType into the database.
        DbShiftTypeHelper.createDbShiftType(dbShiftType);

        return ok();
    }

    public Result deleteShiftTypeByName(String name) {
        DbShiftType dbShiftTypeToDelete = DbShiftTypeHelper.readDbShiftTypeByName(name);
        DbShiftTypeHelper.deleteDbShiftType(dbShiftTypeToDelete);
        return ok();
    }

    public Result readShiftTypesByTeamId(Integer teamId) {
        List<DbShiftType> dbShiftTypeList = ScheduleUtil.getShiftTypeListByTeamId(teamId);
        return ok(Json.toJson(dbShiftTypeList));
    }
}
