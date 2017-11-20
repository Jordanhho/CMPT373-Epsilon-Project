package auth;

import be.objectify.deadbolt.java.cache.HandlerCache;
import com.google.inject.AbstractModule;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.config.CasProtocol;
import org.pac4j.core.authorization.authorizer.RequireAnyRoleAuthorizer;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.http.HttpActionAdapter;
import org.pac4j.play.CallbackController;
import org.pac4j.play.LogoutController;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.deadbolt2.Pac4jHandlerCache;
import org.pac4j.play.deadbolt2.Pac4jRoleHandler;
import org.pac4j.play.http.DefaultHttpActionAdapter;
import org.pac4j.play.store.PlayCacheSessionStore;
import org.pac4j.play.store.PlaySessionStore;
import play.Configuration;
import play.Environment;
import play.Logger;
import play.cache.SyncCacheApi;
import play.mvc.Http;
import play.mvc.Result;

public class SecurityModule extends AbstractModule {


    private static class RoleHandler implements Pac4jRoleHandler { }
    private static Configuration configuration;

    public SecurityModule(final Environment environment, final Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        bind(HandlerCache.class).to(Pac4jHandlerCache.class);
        bind(Pac4jRoleHandler.class).to(RoleHandler.class);
        PlayCacheSessionStore playCacheSessionStore = new PlayCacheSessionStore(getProvider(SyncCacheApi.class));
        bind(PlaySessionStore.class).toInstance(playCacheSessionStore);
        final String baseUrl = "http://cmpt373-1177e.cmpt.sfu.ca:9000"; // TODO: put this into conf
        final String baseUrlDev = "http://localhost:9000";
        CasConfiguration casConfiguration = new CasConfiguration("https://cas.sfu.ca/cas/login");
        casConfiguration.setProtocol(CasProtocol.CAS30);
        casConfiguration.setDefaultTicketValidator(new SfuCasTicketValidator("https://cas.sfu.ca"));
        CasClient casClient = new CasClient(casConfiguration);

        Clients clients = new Clients(baseUrlDev + "/callback", casClient);
        Config config = new Config(clients);
        config.setHttpActionAdapter(new DefaultHttpActionAdapter());
        bind(Config.class).toInstance(config);

        CallbackController callbackController = new CallbackController();
        callbackController.setDefaultUrl("/");
        bind(CallbackController.class).toInstance(callbackController);

        LogoutController logoutController = new LogoutController();
        logoutController.setDefaultUrl("/?defaulturlafterlogout");
        bind(LogoutController.class).toInstance(logoutController);
    }


}
