package controllers;
import com.google.inject.Inject;
import models.databaseModel.scheduling.DbUser;
import play.data.Form;
import play.mvc.*;
import play.data.FormFactory;



import static models.databaseModel.helpers.DbUserHelper.createDbUser;


public class UserController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result listUsers() {
        return ok();
    }

    public Result createUser() {
        Form<DbUser> userForm = formFactory.form(DbUser.class).bindFromRequest();
        DbUser newUser = userForm.get();
        createDbUser(
                newUser.getContactEmail(),
                newUser.getSfuEmail(),
                newUser.getPhoneNumber(),
                newUser.getPhotoURL());

        System.out.println(newUser);
        System.out.println("Successfully created new user");
        return ok(newUser.toString());
    }

    public Result retrieveUser(int id) {
        return ok();
    }

    public Result updateUser(int id) {
        return ok();
    }

    public Result deleteUser(int id) {
        return ok();
    }



}
