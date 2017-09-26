package models.scheduling;

import models.TimeBlock;
import models.id.ShiftId;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.ebean.*;

@Entity
public class Shift extends Model {

    // Fields
    @Id
    @Nonnull
    private ShiftId id;
    @Nonnull
    private TimeBlock timeBlock;
    @Nonnull
    private boolean wasPresent;

    // Constructors
    @Nonnull

    public Shift(@Nonnull TimeBlock timeBlock, @Nonnull ShiftId id, @Nonnull boolean wasPresent) {
        this.timeBlock = timeBlock;
        this.id = id;
        this.wasPresent = wasPresent;
    }
    // Getters= eventId;

    @Nonnull
    public ShiftId getId() {
        return id;
    }

    @Nonnull
    public boolean isWasPresent() {
        return wasPresent;
    }

    @Nonnull
    public TimeBlock getTimeBlock() {
        return timeBlock;
    }

    // Immutable updater
    //    @Nonnull
    //    public Shift settingEventId(ShiftID shiftID) {
    //        Shift copy = this.copy(this);
    //        copy.eventId = Optional.of(shiftID);
    //        return copy;
    //    }
    public static Finder<Integer, Shift> find = new Finder<>(Shift.class);
}