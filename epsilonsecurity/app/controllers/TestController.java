package controllers;

import auth.AuthenticatedAction;
import auth.AuthenticationAnnotation;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

public class TestController extends Controller {

    @AuthenticationAnnotation(permissions = {AuthenticationAnnotation.Permission.ALL})
    public Result testy() {
        Logger.debug("Testy was called");
        return ok("It worked this time!");
    }

    public Result test() {
        return ok("Okay");
    }

}
