package controllers;

import play.mvc.*;
import views.html.index;
import models.Garbage;
import java.util.List;
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
        List<Garbage> trash = Garbage.find.all();
        for(Garbage item : trash) {
            System.out.println(item.string);

            System.out.println(item.key);

            System.out.println(item.number);
        }
        return ok(views.html.index.render(trash));
    }

}
