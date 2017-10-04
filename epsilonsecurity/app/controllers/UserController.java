package controllers;

import models.databaseModel.helpers.DbUserHelper;
import models.databaseModel.scheduling.DbUser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Map;


public class UserController extends Controller {

    public Result createUser() {

        final Map<String, String[]> values = request().body().asFormUrlEncoded();

        String contactEmail = values.get(DbUser.FORM_CONTACT_EMAIL)[0];
        String sfuEmail = values.get(DbUser.FORM_SFU_EMAIL)[0];
        String phoneNumber = values.get(DbUser.FORM_PHONE_NUMBER)[0];
        String photoURL = values.get(DbUser.FORM_PHOTO_URL)[0];

        DbUserHelper.createDbUser(contactEmail, sfuEmail, phoneNumber, photoURL);

        return ok();
    }

    public Result retrieveUser(String sfuEmail) {
        return ok();
    }

    public Result updateUser(String sfuEmail) {
        return ok();
    }

    public Result deleteUser() {

        final Map<String, String[]> values = request().body().asFormUrlEncoded();

        String sfuEmail = values.get(DbUser.FORM_SFU_EMAIL)[0];

        DbUser targetUser = DbUserHelper.readDbUserBySfuEmail(sfuEmail);
        DbUserHelper.deleteDbUserById(targetUser.getUserId());

        return ok();
    }

}
