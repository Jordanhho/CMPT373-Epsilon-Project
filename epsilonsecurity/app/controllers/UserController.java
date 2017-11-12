package controllers;

import models.databaseModel.helpers.DbUserHelper;
import models.databaseModel.scheduling.DbUser;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;


public class UserController extends Controller {

    private final FormFactory formFactory;

    @Inject
    public UserController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    private DbUser getDbUserFromForm() {

        // From the request, create a form that can handle a DbUser object.
        Form<DbUser> form = formFactory.form(DbUser.class).bindFromRequest();

        // Create a DbUser object from the form data.
        DbUser dbUser = form.get();

        return dbUser;
    }

    public Result createUser() {

        DbUser dbUser = getDbUserFromForm();

        // Enter the DbUser into the database.
        DbUserHelper.createDbUser(dbUser);

        return ok();
    }

    public Result retrieveUser(String sfuEmail) {
        DbUser dbUser = DbUserHelper.readDbUserBySfuEmail(sfuEmail);

        return ok(Json.toJson(dbUser));
    }

    public Result readUserByUserId(Integer userId) {
        DbUser dbUser = DbUserHelper.readDbUserById(userId);

        return ok(Json.toJson(dbUser));
    }

    public Result readAllUsers() {
        List<DbUser> dbUserList = DbUserHelper.readAllDbUsers();

        return ok(Json.toJson(dbUserList));
    }

    public Result updateUser(Integer userId) {
        DbUser dbUser = getDbUserFromForm();
        dbUser.setId(userId);
        dbUser.update();

        return ok();
    }

    public Result updateUsersEnabledStatus(Integer userId, Boolean isEnabled){
        DbUserHelper.updateUserEnable(userId, isEnabled);

        return ok();
    }

    public Result deleteUser(DbUser user){
        DbUserHelper.deleteDbUser(user);

        return ok();
    }

    public Result deleteUserBySfuEmail(String sfuEmail) {
        DbUser dbUserToDelete = DbUserHelper.readDbUserBySfuEmail(sfuEmail);
        DbUserHelper.deleteDbUser(dbUserToDelete);

        return ok();
    }
}
