package controllers;

import models.DummyDatabase.DummyDataBase;
import models.databaseModel.scheduling.DbUser;
import org.pac4j.cas.profile.CasProxyProfile;
import play.Environment;
import play.mvc.*;
import views.html.index;

import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.play.PlayWebContext;
import org.pac4j.core.config.Config;
import org.pac4j.play.store.PlaySessionStore;

import play.libs.Json;

import javax.inject.Inject;
import java.util.List;

public class HomeController extends Controller {

    private final Environment env;

    @Inject
    private Config config;
    @Inject
    private PlaySessionStore playSessionStore;

    @Inject
    public HomeController(Environment env) {
        this.env = env;
    }

    //@Secure(clients = "AnonymousClient", authorizers = "csrfToken")
    public Result index(String id) {
        if(DbUser.find.query().findCount() == 0){
            new DummyDataBase();
        }
        return status(200, index.render(env, 3000, "bundle.js", "bundle.css"));
    }

    private List<CommonProfile> getProfiles() {
        final PlayWebContext context = new PlayWebContext(ctx(), playSessionStore);
        final ProfileManager<CommonProfile> profileManager = new ProfileManager(context);
        return profileManager.getAll(true);
    }

//    private Result protectedIndexView() {
//        return status(200, Json.toJson( getProfiles() ));
//    }

//    @Secure(clients = "CasClient")
    public Result casUser() {
        final CommonProfile profile = getProfiles().get(0);
//        final String service = "http://localhost:8080/proxiedService";
//        String proxyTicket = null;
//        if (profile instanceof CasProxyProfile) {
//            final CasProxyProfile proxyProfile = (CasProxyProfile) profile;
//            proxyTicket = proxyProfile.getProxyTicketFor(service);
//        }
//        return ok(views.html.casProtectedIndex.render(profile, service, proxyTicket));
        return status(200, Json.toJson( profile ));
    }
}
