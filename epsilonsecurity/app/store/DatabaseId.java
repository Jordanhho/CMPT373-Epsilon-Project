package store;

import javax.annotation.Nonnull;

public abstract class DatabaseId {
    @Nonnull protected Integer rawId;

    @Nonnull
    public Integer getRawId() {
        return rawId;
    }

    public DatabaseId(Integer rawID) {
        this.rawId = rawID;
    }
}
