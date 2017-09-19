package models;

import javax.annotation.Nonnull;

public class UserId
        extends DatabaseId
        implements UserIdable {

    public UserId(long rawID) {
        super(rawID);
    }

    @Nonnull
    @Override
    public UserId getUserId() {
        return this;
    }
}
