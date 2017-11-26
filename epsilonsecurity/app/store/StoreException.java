package store;

import javax.annotation.Nullable;

public class StoreException extends Exception {
    private Table table;
    private Reason reason;

    enum Reason {
        ID_NOT_FOUND, // TODO: add as they come up
        EXISTS
    }

    enum Table {
        USER // TODO: add as we implement stores
    }

    public StoreException(StoreException.Table table, StoreException.Reason reason) {
        new StoreException(table, reason, null);
    }

    public StoreException(StoreException.Table table, StoreException.Reason reason, @Nullable String message) {
        super(message);
        this.table = table;
        this.reason = reason;
    }
}
