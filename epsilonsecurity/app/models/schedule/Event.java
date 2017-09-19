package models.schedule;

import models.EventId;
import models.TimeBlock;
import models.UserId;
import models.UserIdable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class Event
        implements UserIdable {

    // Fields
    @Nonnull private UserId userId;
    @Nonnull private TimeBlock timeBlock;
    @Nonnull private Optional<EventId> eventId; // TODO: figure out how to initialize this

    // Constructors
    @Nonnull
    public Event(@Nonnull UserIdable userIDable, @Nonnull TimeBlock timeBlock, @Nonnull Optional<EventId> eventId) {
        this.userId = userIDable.getUserId();
        this.timeBlock = timeBlock;
        this.eventId = eventId;
    }

    // Getters
    @Override
    public UserId getUserId() {
        return userId;
    }

    @Nonnull
    public TimeBlock getTimeBlock() {
        return timeBlock;
    }

    // Immutable updater
    @Nonnull
    public Event settingEventId(EventId eventId) {
        Event copy = this.copy(this);
        copy.eventId = Optional.of(eventId);
        return copy;
    }

    // Private Interface
    @Nonnull
    private Event copy(@Nonnull Event event) {
        return new Event(event.userId, event.timeBlock, event.eventId);
    }

}