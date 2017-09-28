package controllers;
import models.databaseModel.helpers.UserDbHelper;
import play.mvc.*;



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
//        UserDbHelper.createDbUser("three", "four", "five", "six");
//        UserDbHelper.deleteDbUserById(1);
//        DbUser dbUser = UserDbHelper.readDbUserById(1);
//        System.out.println(dbUser.getRoleId());
        return ok(views.html.index.render());
    }

}


