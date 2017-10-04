package controllers;

import com.google.inject.Inject;
import models.databaseModel.helpers.DbUserHelper;
import models.databaseModel.scheduling.DbUser;
import play.mvc.*;
import play.data.FormFactory;


import java.util.Map;

import static models.databaseModel.helpers.DbUserHelper.createDbUser;
import static models.databaseModel.helpers.DbUserHelper.deleteDbUserById;
import static models.databaseModel.helpers.DbUserHelper.readDbUserBySfuEmail;


public class UserController extends Controller {

    public Result createUser() {
        final Map<String, String[]> values = request().body().asFormUrlEncoded();

        String contactEmail = values.get("contactEmail")[0];
        String sfuEmail = values.get("sfuEmail")[0];
        String phoneNumber = values.get("phoneNumber")[0];
        String photoURL = values.get("photoURL")[0];

        DbUserHelper.createDbUser(contactEmail, sfuEmail, phoneNumber, photoURL);

        return ok();
    }

    public Result retrieveUser(String sfuEmail) {
        DbUser targetUser = readDbUserBySfuEmail(sfuEmail);
        System.out.println("Retrieved user with " + targetUser.getSfuEmail());
        return ok();
    }

    public Result updateUser(String sfuEmail) {
        return ok();
    }

    public Result deleteUser(String sfuEmail) {
        DbUser targetUser = readDbUserBySfuEmail(sfuEmail);
        deleteDbUserById(targetUser.getUserId());
        System.out.println("Deleted user with " + targetUser.getSfuEmail());
        return ok();
    }

}
