package controllers;

import auth.AuthenticationAnnotation;
import org.pac4j.play.java.Secure;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import auth.dsl.*;

import static auth.dsl.AuthDSL.*;


public class TestController extends Controller {

    @Secure
    @AuthenticationAnnotation(permissions = {READ+IN+USER_LIST, WRITE+IN+SCHEDULING})
    public Result testy() {
        Logger.debug("Testy was called");
        return ok("It worked this time!");
    }

    public Result test() {
        return ok("Okay");
    }

}
