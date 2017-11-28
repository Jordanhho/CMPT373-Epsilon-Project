package auth;

import be.objectify.deadbolt.java.cache.HandlerCache;
import com.google.inject.AbstractModule;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.config.CasProtocol;
import org.pac4j.core.authorization.authorizer.RequireAnyRoleAuthorizer;
import org.pac4j.core.client.Clients;
//import org.pac4j.core.config.Config;
import org.pac4j.core.http.HttpActionAdapter;
import org.pac4j.play.CallbackController;
import org.pac4j.play.LogoutController;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.deadbolt2.Pac4jHandlerCache;
import org.pac4j.play.deadbolt2.Pac4jRoleHandler;
import org.pac4j.play.http.DefaultHttpActionAdapter;
import org.pac4j.play.store.PlayCacheSessionStore;
import org.pac4j.play.store.PlaySessionStore;
import play.Logger;
import play.cache.SyncCacheApi;
import play.mvc.Http;
import play.mvc.Result;

//------------------------

import javax.inject.Inject;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import play.Environment;

public class SecurityModule extends AbstractModule {


    private static class RoleHandler implements Pac4jRoleHandler { }
    private static final String     API_SERVER_ADDRESS_KEY = "API_SERVER_ADDRESS";

    private final Environment env;
    private final Config config;

    @Inject
    public SecurityModule(Environment env, Config config) {
        this.env = env;
        this.config = config;
    }

    @Override
    protected void configure() {
        bind(HandlerCache.class).to(Pac4jHandlerCache.class);
        bind(Pac4jRoleHandler.class).to(RoleHandler.class);
        PlayCacheSessionStore playCacheSessionStore = new PlayCacheSessionStore(getProvider(SyncCacheApi.class));
        bind(PlaySessionStore.class).toInstance(playCacheSessionStore);


        String baseUrl = this.getApiServerUrl();

        CasConfiguration casConfiguration = new CasConfiguration("https://cas.sfu.ca/cas/login");
        casConfiguration.setProtocol(CasProtocol.CAS30);
        casConfiguration.setDefaultTicketValidator(new SfuCasTicketValidator("https://cas.sfu.ca"));
        CasClient casClient = new CasClient(casConfiguration);

        Clients clients = new Clients(baseUrl + "/callback", casClient);
        org.pac4j.core.config.Config configx = new org.pac4j.core.config.Config(clients);
        configx.setHttpActionAdapter(new DefaultHttpActionAdapter());
        bind(org.pac4j.core.config.Config.class).toInstance(configx);

        // callback
        CallbackController callbackController = new CallbackController();
        callbackController.setDefaultUrl("/");
        bind(CallbackController.class).toInstance(callbackController);

        // logout
        LogoutController logoutController = new LogoutController();
//        logoutController.setDefaultUrl("https://cas.sfu.ca/cas/logout");
//        logoutController.setDefaultUrl("/?defaulturlafterlogout");
        logoutController.setDestroySession(true);
        bind(LogoutController.class).toInstance(logoutController);
    }

    public String getApiServerUrl() {
        if (this.config.hasPath(API_SERVER_ADDRESS_KEY)) {
            return this.config.getString(API_SERVER_ADDRESS_KEY);
        } else {
            throw new ConfigException.Missing(API_SERVER_ADDRESS_KEY);
        }
    }
}
