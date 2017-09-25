package models;

import javax.annotation.Nonnull;



public abstract class DatabaseId {
    @Nonnull protected long rawId;

    @Nonnull
    public long getRawId() {
        return rawId;
    }

    public DatabaseId(long rawID) {
        this.rawId = rawID;
    }
}
