package controllers;

import auth.AuthenticationAnnotation;
import models.DummyDatabase.DummyDataBase;
import org.pac4j.play.java.Secure;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

import static auth.dsl.AuthDSL.*;


public class TestController extends Controller {

    // Examples of how to annotate methods. Multiple requirements are just seperated by a comma
    // since this is an Array
    // just make sure to import the following:
    // import static auth.dsl.AuthDSL.*;
    @Secure
    @AuthenticationAnnotation(permissions = {READ+IN+USER_LIST})
    public Result testCanReadUsers() {
        return ok("You can read users!");
    }

    @Secure
    @AuthenticationAnnotation(permissions = {WRITE+IN+SCHEDULING})
    public Result testCanWriteSchedule() {
        return ok("You can read scheduling!");
    }

    @Secure
    @AuthenticationAnnotation(permissions = {})
    public Result alwaysWorks() {
        Logger.debug("Always successful function called");
        return ok("This was successful");
    }

    public Result test() {
        return ok("Okay");
    }

}
