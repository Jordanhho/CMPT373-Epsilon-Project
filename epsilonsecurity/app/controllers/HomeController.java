package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.DummyDatabase.DummyDataBase;
import models.databaseModel.helpers.DbUserHelper;
import models.databaseModel.scheduling.DbUser;
import org.junit.Assert;
import org.pac4j.cas.profile.CasProxyProfile;
import play.Environment;
import play.libs.concurrent.HttpExecutionContext;
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
import java.util.concurrent.CompletionStage;
import static java.util.concurrent.CompletableFuture.supplyAsync;

public class HomeController extends Controller {

    private final Environment env;
    private PlaySessionStore playSessionStore;
    private HttpExecutionContext httpExecutionContext;

    @Inject
    public HomeController(Environment env, PlaySessionStore playSessionStore, HttpExecutionContext ec) {
        this.env = env;
        this.playSessionStore = playSessionStore;
        this.httpExecutionContext = ec;
    }

    public Result index(String id) {
        if (DbUser.find.query().findCount() == 0) {
            new DummyDataBase();
        }
        return status(200, index.render(env, 3000, "bundle.js", "bundle.css"));
    }

    private List<CommonProfile> getProfiles() {
        final PlayWebContext context = new PlayWebContext(ctx(), playSessionStore);
        final ProfileManager<CommonProfile> profileManager = new ProfileManager(context);
        return profileManager.getAll(true);
    }

    public CompletionStage<Result> casUser() {

        return supplyAsync(() -> {
            // get the authenticated user's sfu username
            final CommonProfile profile = getProfiles().get(0);
            String sfuUsername = profile.getId();

            // fetch the corresponding user form dbase
            DbUser user = DbUserHelper.readDbUserByUsername(sfuUsername);

            // if user does not exist
            if (!(user instanceof DbUser)) {
                return status(404);
            }

            // if user exists
            return status(200, Json.toJson(user));
        }, httpExecutionContext.current());
    }
}

/*
try {
    //*
    // Get SFU username authenticated user
    // Note: SFU CAS only returns username, no other valuable info like first and last name.
    final CommonProfile profile = getProfiles().get(0);
    String sfuUsername = profile.getId();
    System.out.println("> username: " + sfuUsername);

    // get user from database with the sfuUsername
    DbUser user = DbUserHelper.readDbUserByUsername(sfuUsername);

    if (!(user instanceof DbUser)) {
        System.out.println("> user not found");
        return status(404);
    }

    return status(200, Json.toJson(user));
} catch (Exception e) {
    return status(500, "An exception occured: " + e.getLocalizedMessage());
}
//*/

/*
// for debugging

ObjectNode dummyUser = Json.newObject();
dummyUser.put("id", 123);
dummyUser.put("username", "jdoe");
dummyUser.put("email", "jdoe@sfu.ca");
dummyUser.put("firstName", "john");
dummyUser.put("lastName", "doe");
dummyUser.put("photo", "https://randomuser.me/api/portraits/men/83.jpg");
dummyUser.put("role", "admin");

return status(200, dummyUser);
*/