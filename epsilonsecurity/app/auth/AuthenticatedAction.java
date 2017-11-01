package auth;

import models.AuthToken;
import play.Logger;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static auth.AuthenticationAnnotation.Permission.ALL;
import static auth.AuthenticationAnnotation.Permission.NONE;

public class AuthenticatedAction extends Action<AuthenticationAnnotation> {

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        Logger.debug("Hit the Authentication Action");
        if (hasPermission(ctx)) {
            return delegate.call(ctx);
        } else {
            CompletableFuture<Result> future = new CompletableFuture<>();
            future.complete(forbidden("Nope"));
            return future;
        }
    }

    private Boolean hasPermission(Http.Context ctx) {
        return extractToken(ctx)
                .map(this::permissionsForToken)
                .map( permissions -> areAllPermissionsPresent(permissions, ctx) )
                .orElse(false);
    }

    private Optional<AuthToken> extractToken(Http.Context ctx) {
        return Optional.ofNullable(new AuthToken()); // TODO: implement
    }

    private AuthenticationAnnotation.Permission[] permissionsForToken(AuthToken token) {
        if (Instant.now().getEpochSecond() % 2 == 0) {
            return new AuthenticationAnnotation.Permission[]{ALL};
        } else {
            return new AuthenticationAnnotation.Permission[]{NONE};
        }
    }

    private Boolean areAllPermissionsPresent(AuthenticationAnnotation.Permission[] permissions, Http.Context ctx) {
        List<AuthenticationAnnotation.Permission> expected = Arrays.asList(configuration.permissions());
        for (AuthenticationAnnotation.Permission permission : permissions) {
            if (expected.contains(permission) == false) { return false; }
        }
        return true;
    }

}
