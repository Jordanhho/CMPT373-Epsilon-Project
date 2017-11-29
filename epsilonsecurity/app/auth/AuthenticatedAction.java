package auth;

import com.google.inject.Inject;
import models.AuthToken;
import org.pac4j.cas.profile.CasProfile;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.store.PlaySessionStore;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import store.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class AuthenticatedAction extends Action<AuthenticationAnnotation> {

    protected PlaySessionStore sessionStore;

    @Inject
    public AuthenticatedAction(PlaySessionStore sessionStore) {
        this.sessionStore = sessionStore;
    }

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        if (hasPermission(ctx)) {
            return delegate.call(ctx);
        } else {
            CompletableFuture<Result> future = new CompletableFuture<>();
            // TODO: redirect to a nice forbidden page
            future.complete(forbidden("You do not have permission to access this content."));
            return future;
        }
    }

    private Boolean hasPermission(Http.Context ctx) {
        return extractToken(ctx)
                .flatMap(this::userForToken)
                .map(this::areAllPermissionsPresent)
                .orElse(false);
    }

    private Optional<AuthToken> extractToken(Http.Context ctx) {
        PlayWebContext context = new PlayWebContext(ctx, sessionStore);
        ProfileManager<CasProfile> profileManager = new ProfileManager<CasProfile>(context);
        Optional<AuthToken> token = profileManager.get(true)
            .map(CasProfile::getId)
            .map(AuthToken::new);
        return token;
    }

    private Optional<PermissionedUser> userForToken(AuthToken token) {
        Optional<PermissionedUser> maybeUser;
        UserStore store = UserStore.getInstance();
        try {
            PermissionedUser user = store.getUserForAuthToken(token)
                .thenCompose(store::getPermissionedUserForId)
                .get();
            maybeUser = Optional.of(user);
        } catch (InterruptedException e) {
            // if we encountered an execution/interrupted exception
            // we have bigger problems than returning nothing
            e.printStackTrace();
            maybeUser = Optional.empty();
        } catch (ExecutionException e) {
            e.printStackTrace();
            maybeUser = Optional.empty();
        }
        return maybeUser;
    }

    private Boolean areAllPermissionsPresent(PermissionedUser user) {
        List<String> encodedPermissions = Arrays.asList(configuration.permissions());
        return Permissions.userDoesHaveEncodedPermissions(user, encodedPermissions);

    }

}
