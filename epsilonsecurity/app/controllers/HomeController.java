package controllers;
import models.databaseModel.helpers.*;
import models.databaseModel.scheduling.DbOneTimeAvailability;
import models.databaseModel.scheduling.DbUser;
import models.databaseModel.scheduling.DbUserShift;
import models.queries.ScheduleUtil;
import play.mvc.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;


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
      public Result index() {
        return ok(views.html.index.render());
        }

    // can be used to test a certain field, replace passed in argument with whatever you need to test
    public Result test(String contactEmail) {
        System.out.println(contactEmail);
        return ok(contactEmail);
    }

    // todo: create invalid query request function (all paths in routes file)
}


