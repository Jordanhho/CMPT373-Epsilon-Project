package controllers;

import models.databaseModel.helpers.DbShiftNameHelper;
import models.databaseModel.scheduling.DbShiftName;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class ShiftNameController extends Controller {

    private final FormFactory formFactory;

    @Inject
    ShiftNameController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    private DbShiftName getDbShiftNameFromForm() {

        // From the request, create a form that can handle a DbShiftName object
        Form<DbShiftName> form = formFactory.form(DbShiftName.class).bindFromRequest();

        // Create a DbShiftName object from the form data.
        DbShiftName dbShiftName = form.get();

        return dbShiftName;
    }

    public Result createShiftName() {

        DbShiftName dbShiftName = getDbShiftNameFromForm();

        // Enter the DbShiftName into the database.
        DbShiftNameHelper.createDbShiftName(dbShiftName);

        return ok();
    }

    public Result deleteShiftName(String name) {
        DbShiftName dbShiftNameToDelete = DbShiftNameHelper.readDbShiftNameByName(name);
        DbShiftNameHelper.deleteDbShiftName(dbShiftNameToDelete);
        return ok();
    }

}
