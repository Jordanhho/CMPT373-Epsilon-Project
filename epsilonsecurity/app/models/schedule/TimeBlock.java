package models.schedule;

import org.joda.time.DateTime;

import java.time.Duration;
import java.util.List;

public class TimeBlock {
    public DateTime start;
    public DateTime end;

    // TODO: requires initializer

    public Duration getDuration() {
        return Duration.between(start, end);
    }

    public List<TimeSlot> getTimeSlots() {
        return TimeSlotFactory.newTimeSlots(start, end);
    }
}
