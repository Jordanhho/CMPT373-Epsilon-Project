package controllers;

import org.pac4j.play.LogoutController;

public class CentralLogoutController extends LogoutController {

    public CentralLogoutController() {
//        setDefaultUrl("https://cas.sfu.ca/cas/appLogout?app=my+app+desc");
//        setDefaultUrl("http://localhost:9000/?defaulturlafterlogoutafteridp");
        setLocalLogout(false);
        setCentralLogout(true);
//        setLogoutUrlPattern("http://localhost:9000/.*");
    }
}
