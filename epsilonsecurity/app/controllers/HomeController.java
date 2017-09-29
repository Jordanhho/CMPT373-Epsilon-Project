package controllers;
import ch.qos.logback.core.db.DBHelper;
import models.databaseModel.helpers.DbShiftHelper;
import models.databaseModel.scheduling.DbShift;
import models.databaseModel.scheduling.DbUser;
import play.mvc.*;

import java.util.List;

import static models.databaseModel.helpers.DbShiftHelper.readDbShiftByTime;
import static models.databaseModel.helpers.DbUserHelper.createDbUser;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 *
 * Handles Rest API calls
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    //public Result index(String path) {
      public Result index() {
//        DbUserHelper.createDbUser("three", "four", "five", "six");
//        DbUserHelper.deleteDbUserById(1);
//        DbUser dbUser = DbUserHelper.readDbUserById(1);
//        System.out.println(dbUser.getRoleId());
          //DbShiftHelper.createDbShift(1000, 2000);
          //DbShiftHelper.createDbShift(1000, 2000);
          //DbShiftHelper.createDbShift(2000, 3000);
          /*List<DbShift> shifts = DbShiftHelper.readDbShiftByTime(1000, 2000);
          System.out.println(shifts.size());*/
          /*DbShiftHelper.deleteShiftById(1);
          DbShiftHelper.deleteShiftById(2);
          DbShiftHelper.deleteShiftById(3);*/
          return ok(views.html.index.render());
    }

    public Result listUsers() {
        return ok();
    }

    public Result create(String contactEmail, String sfuEmail, String phoneNumber) {
        DbUser newUser = new DbUser(contactEmail, sfuEmail, phoneNumber, "url");
        createDbUser(contactEmail, sfuEmail, phoneNumber, "url");
        System.out.println(newUser);
        System.out.println("Successfully created new user");
        return ok(newUser.toString());
    }

    public Result test(String contactEmail) {
        System.out.println(contactEmail);
        return ok(contactEmail);
    }

    public Result retrieve(int id) {
        return ok();
    }

    public Result update(int id) {
        return ok();
    }

    public Result delete(int id) {
        return ok();
    }

    // todo: create invalid query request function (all paths in routes file)
}


