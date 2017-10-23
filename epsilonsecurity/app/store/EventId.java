package store;

import javax.annotation.Nonnull;

public class EventId extends DatabaseId {

    public EventId(@Nonnull Integer rawId) {
        super(rawId);
    }

}
