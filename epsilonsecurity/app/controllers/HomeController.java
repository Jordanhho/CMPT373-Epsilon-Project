package controllers;

import models.DummyDatabase.DummyDataBase;
import models.databaseModel.scheduling.DbUser;
import play.Environment;
import play.mvc.*;
import views.html.index;

import javax.inject.Inject;

/*
Reorganizing the router file has made this controller obsolete
It is simply kept here for probable future use.
*/
public class HomeController extends Controller {

    private final Environment env;

    @Inject
    public HomeController(Environment env) {
        this.env = env;
    }
    public Result index(String id) {
        if(DbUser.find.query().findCount() == 0){
            new DummyDataBase();
        }
        return status(200, index.render(env, 3000, "bundle.js", "bundle.css"));
    }
}
