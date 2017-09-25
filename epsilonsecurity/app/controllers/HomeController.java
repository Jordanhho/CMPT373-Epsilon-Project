package controllers;

import models.SomeModel;
import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
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

    public Result create() {
        // Save entry into the database
        new SomeModel().save();
        return ok(views.html.index.render());
    }

    public Result delete(Integer id) {
        SomeModel someModel = SomeModel.find.byId(id);
        someModel.delete();
        return ok(views.html.index.render());
    }
}
