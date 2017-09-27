package models.schedule;

import org.jetbrains.annotations.NotNull;

import java.sql.Time;
import java.time.Duration;
import java.time.Instant;

import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

public class TimeBlock {
    public final Instant start;
    public final Instant end;

    public Duration getDuration() {
        return Duration.between(start, end);
    }

    public List<TimeSlot> getTimeSlots() {
        return TimeSlotFactory.newTimeSlots(start, end);
    }

    // TODO: quantize? Figure out exactly how this should work and at what access level
    protected TimeBlock(Instant start, Instant end) {
        // make sure end is after start?
        this.start = start;
        this.end = end;
    }

    @NotNull
    public static TimeBlock single(Instant start) {
        return new TimeBlock(start, start.plus(TimeSlot.QUANTIZATION_MINUTES, MINUTES));
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof TimeBlock) == false) { return false; }
        TimeBlock other = (TimeBlock) obj;
        boolean sameStart = other.start.equals(this.start);
        boolean sameEnd = other.end.equals(this.end);
        return sameStart && sameEnd;
    }
}
