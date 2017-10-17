package models;

import javax.annotation.Nonnull;

public abstract class DatabaseId {
    @Nonnull protected int rawId;

    @Nonnull
    public long getRawId() {
        return rawId;
    }

    public DatabaseId(int rawID) {
        this.rawId = rawID;
    }
}
