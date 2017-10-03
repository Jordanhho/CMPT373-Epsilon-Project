package controllers;
import models.databaseModel.helpers.*;
import models.databaseModel.scheduling.DbOneTimeAvailability;
import models.databaseModel.scheduling.DbUser;
import models.databaseModel.scheduling.DbUserShift;
import models.queries.ScheduleUtil;
import play.mvc.*;

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
    //public Result index(String path) {
      public Result index() {
          //test database
/*
          DbUserHelper.createDbUser("", "", "", "");
          DbUserHelper.createDbUser("", "", "", "");
          DbUserHelper.createDbUser("", "", "", "");
          DbUserHelper.createDbUser("", "", "", "");
          DbUserHelper.createDbUser("", "", "", "");

          DbUserTeamHelper.createDbUserTeam(1, 1);
          DbUserTeamHelper.createDbUserTeam(1, 2);
          DbUserTeamHelper.createDbUserTeam(1, 3);
          DbUserTeamHelper.createDbUserTeam(1, 4);
          DbUserTeamHelper.createDbUserTeam(1, 5);

          DbUserShiftHelper.createDbUserShift(1, 1);
          DbUserShiftHelper.createDbUserShift(2, 2);
          DbUserShiftHelper.createDbUserShift(3, 3);
          DbUserShiftHelper.createDbUserShift(4, 4);
          DbUserShiftHelper.createDbUserShift(5, 5);

          DbShiftHelper.createDbShift(700, 1000);
          DbShiftHelper.createDbShift(1200, 1500);
          DbShiftHelper.createDbShift(1600, 1900);
          DbShiftHelper.createDbShift(2200, 2300);
          DbShiftHelper.createDbShift(2200, 2300);

          DbOneTimeAvailabilityHelper.createDbOneTimeAvailability(1,1600, 1900);
          DbOneTimeAvailabilityHelper.createDbOneTimeAvailability(2,1600, 1900);
          DbOneTimeAvailabilityHelper.createDbOneTimeAvailability(3,1600, 1900);
          DbOneTimeAvailabilityHelper.createDbOneTimeAvailability(4,1600, 1900);
          DbOneTimeAvailabilityHelper.createDbOneTimeAvailability(4,1600, 1630);

          DbOneTimeUnavailabilityHelper.createDbOneTimeUnavailability(1, 700, 1000);
          DbOneTimeUnavailabilityHelper.createDbOneTimeUnavailability(2, 1600, 1900);
          DbOneTimeUnavailabilityHelper.createDbOneTimeUnavailability(3, 1200, 1500);
          DbOneTimeUnavailabilityHelper.createDbOneTimeUnavailability(4, 2100, 2300);
          DbOneTimeUnavailabilityHelper.createDbOneTimeUnavailability(5, 2100, 2300);*/

          List<DbUser> dbUsers = ScheduleUtil.queryUsersBasedOnAvailiability(1, 1530, 1700);
          System.out.println("HI");
          System.out.println("Time Range: 1530 - 1700");
          for(DbUser user : dbUsers){
              System.out.println("Available user" + user.getUserId());
              System.out.println("Time Range: "
                      + DbOneTimeAvailabilityHelper.readDbOneTimeAvailabilityById(user.getUserId()).getTimeStart()
                      + " - "
                      + DbOneTimeAvailabilityHelper.readDbOneTimeAvailabilityById(user.getUserId()).getTimeEnd());
          }
        return ok(views.html.index.render());
    }


    // can be used to test a certain field, replace passed in argument with whatever you need to test
    public Result test(String contactEmail) {
        System.out.println(contactEmail);
        return ok(contactEmail);
    }

    // todo: create invalid query request function (all paths in routes file)
}


