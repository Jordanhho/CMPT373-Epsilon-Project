package controllers;

import models.databaseModel.helpers.DbUserHelper;
import models.databaseModel.scheduling.DbUser;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;


public class UserController extends Controller {

    private final FormFactory formFactory;

    @Inject
    public UserController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result createUser() {

        // From the request, create a form that can handle a DbUser object.
        Form<DbUser> form = formFactory.form(DbUser.class).bindFromRequest();

        // Create a DbUser object from the form data.
        DbUser dbUser = form.get();

        // Enter the DbUser into the database.
        DbUserHelper.createDbUser(dbUser);

        return ok();
    }

    public Result retrieveUser(String sfuEmail) {
        DbUser dbUser = DbUserHelper.readDbUserBySfuEmail(sfuEmail);

        return ok(Json.toJson(dbUser));
    }

    public Result updateUser(String sfuEmail) {
        return ok();
    }

    public Result deleteUserBySfuEmail() {

        // From the request, create a form that can handle a DbUser object.
        Form<DbUser> form = formFactory.form(DbUser.class).bindFromRequest();

        // Create a DbUser object from the form data.
        DbUser dbUserFromForm = form.get();

        // Read the DbUser to delete based on the form fields.
        DbUser dbUserToDelete = DbUserHelper.readDbUserBySfuEmail(dbUserFromForm.getSfuEmail());

        DbUserHelper.deleteDbUserBySfuEmail(dbUserToDelete);

        return ok();
    }
}
