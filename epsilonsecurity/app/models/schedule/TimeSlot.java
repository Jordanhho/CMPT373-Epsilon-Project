package models.schedule;

import java.time.Instant;
import javax.annotation.Nonnull;

import static java.time.temporal.ChronoUnit.MINUTES;

public class TimeSlot {

    public static int QUANTIZATION_MINUTES = 15;

    @Nonnull
    private Instant start;

    protected TimeSlot(@Nonnull Instant start) {
        this.start = start;
    }

    @Nonnull
    public Instant getEnd() {
        return start.plus(QUANTIZATION_MINUTES, MINUTES);
    }

    @Nonnull
    public Instant getStart() {
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
