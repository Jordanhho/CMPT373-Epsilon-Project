package models.schedule;

import java.time.Duration;
import java.time.Instant;

import java.util.List;

public class TimeBlock {
    public Instant start;
    public Instant end;

    // TODO: requires initializer

    public Duration getDuration() {
        return Duration.between(start, end);
    }

    public List<TimeSlot> getTimeSlots() {
        return TimeSlotFactory.newTimeSlots(start, end);
    }

    public TimeBlock(Instant start, Instant end) {
        this.start = start;
        this.end = end;
    }
}
