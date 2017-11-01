package models.schedule;

import store.EventId;
import store.UserId;
import store.UserIdable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class Shift
        implements UserIdable {

    // Fields
    @Nonnull private UserId userId;
    @Nonnull private TimeBlock timeBlock;
    @Nonnull private Optional<EventId> eventId; // TODO: figure out how to initialize this

    // Constructors
    @Nonnull
    public Shift(@Nonnull UserIdable userIDable, @Nonnull TimeBlock timeBlock, @Nonnull Optional<EventId> eventId) {
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
    public Shift settingEventId(EventId eventId) {
        Shift copy = this.copy(this);
        copy.eventId = Optional.of(eventId);
        return copy;
    }

    // Private Interface
    @Nonnull
    private Shift copy(@Nonnull Shift shift) {
        return new Shift(shift.userId, shift.timeBlock, shift.eventId);
    }

}