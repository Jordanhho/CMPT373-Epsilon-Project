package controllers;

import models.databaseModel.helpers.DbUserHelper;
import models.databaseModel.scheduling.DbUser;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


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

    public Result updateUser(String sfuEmail) {
        return ok();
    }

    public Result deleteUserBySfuEmail() {

        DbUser dbUserFromForm = getDbUserFromForm();

        // Read the DbUser to delete based on the form fields.
        DbUser dbUserToDelete = DbUserHelper.readDbUserBySfuEmail(dbUserFromForm.getSfuEmail());

        DbUserHelper.deleteDbUserBySfuEmail(dbUserToDelete);

        return ok();
    }

    // purpose: simply to test integration of frontend MySchedule component
    // return all shifts for a specific user
    public Result getAllShifts(String sfuEmail) {

        // mock timing
        Calendar now = Calendar.getInstance();
        Calendar nowCopy;
        String start_time;
        String end_time;

        // shift1
        HashMap<String, Object> shift1 = new HashMap<>();
        shift1.put("id", UUID.randomUUID().toString());
        shift1.put("title", "Parkade Watch");
        shift1.put("campus", "Surrey");
        shift1.put("description", "Be on the lookout for a rare Sasquash known to break into cars.");

        nowCopy = (Calendar) now.clone();
        start_time = nowCopy.toInstant().toString();
        nowCopy.add(Calendar.HOUR_OF_DAY, 2);
        end_time = nowCopy.toInstant().toString();
        shift1.put("start", start_time);
        shift1.put("end", end_time);

        // shift2
        HashMap<String, Object> shift2 = new HashMap<>();
        shift2.put("id", UUID.randomUUID().toString());
        shift2.put("title", "Safe Walk");
        shift2.put("campus", "Burnaby");
        shift2.put("description", null);

        nowCopy = (Calendar) now.clone();
        nowCopy.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        nowCopy.set(Calendar.HOUR_OF_DAY, 12);
        start_time = nowCopy.toInstant().toString();
        nowCopy.add(Calendar.HOUR_OF_DAY, 3);
        end_time = nowCopy.toInstant().toString();
        shift2.put("start", start_time);
        shift2.put("end", end_time);

        List shifts = new ArrayList();
        shifts.add(shift1);
        shifts.add(shift2);

//        System.out.println(Json.toJson(shifts));

        return status(200, Json.toJson(shifts));
    }
}
