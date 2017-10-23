package store;

import javax.annotation.Nonnull;

public interface UserIdable {
    @Nonnull UserId getUserId();
}
