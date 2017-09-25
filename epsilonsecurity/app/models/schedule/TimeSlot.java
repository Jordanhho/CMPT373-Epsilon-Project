package models.schedule;

import org.joda.time.DateTime;

import javax.annotation.Nonnull;

public class TimeSlot {

    public static int QUANTIZATION_MINUTES = 15;

    @Nonnull
    private DateTime start;

    protected TimeSlot(@Nonnull DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return start.plusMinutes(QUANTIZATION_MINUTES);
    }

    public DateTime getStart() {
        return start;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TimeSlot)) {
            return false;
        }
        TimeSlot other = (TimeSlot) obj;
        return this.getStart().equals(other.getStart());
    }
}
