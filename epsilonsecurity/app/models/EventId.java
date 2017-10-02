package models;

import javax.annotation.Nonnull;

public class EventId extends DatabaseId {

    public EventId(@Nonnull long rawId) {
        super(rawId);
    }

}
