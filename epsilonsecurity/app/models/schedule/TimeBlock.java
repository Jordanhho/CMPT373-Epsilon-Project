package models.schedule;

import org.jetbrains.annotations.NotNull;

import java.sql.Time;
import java.time.Duration;
import java.time.Instant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

public class TimeBlock {

    private final List<TimeSlot> timeSlots;

    public Duration getDuration() {
        return null; // TODO: Unimplemented
    }

    public List<TimeSlot> getTimeSlots() {
        return this.timeSlots;
    }

    // TODO: quantize? Figure out exactly how this should work and at what access level
    protected TimeBlock(Instant start, Instant end) {
        // make sure end is after start?
        this.timeSlots = TimeSlotFactory.newTimeSlots(start, end);
    }

    public TimeBlock(List<TimeSlot> timeSlots) {
        // TODO:
        // precondition: sorted
        this.timeSlots = timeSlots;
    }

    @NotNull
    public static TimeBlock single(Instant start) {
        return new TimeBlock(Arrays.asList(TimeSlotFactory.newTimeSlotFrom(start)));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if ((obj instanceof TimeBlock) == false) { return false; }
        TimeBlock other = (TimeBlock) obj;
        return this.timeSlots.equals(other.timeSlots);
    }
}
