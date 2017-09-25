package models.schedule;

import java.time.Instant;
import java.time.Duration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.MINUTES;

public class TimeSlotFactory {

    public static TimeSlot newTimeSlotFrom(Instant from) {
        return new TimeSlot(quantize(from));
    }

    // TODO: assertions, e.g. to > from and count > 0

    public static List<TimeSlot> newTimeSlots(Instant from, Instant to) {
        Instant quantizedStart = quantize(from);
        Instant quantizedEnd = quantize(to);
        Duration duration = Duration.between(quantizedStart, quantizedEnd);
        int minutesBetween = (int) duration.get(MINUTES);
        int count = minutesBetween / TimeSlot.QUANTIZATION_MINUTES;
        return newTimeSlots(quantizedStart, count);
    }

    public static List<TimeSlot> newTimeSlots(Instant from, int count) {
        List<TimeSlot> timeSlots = IntStream.range(0, count)
                .mapToObj( offset -> {
                    Instant start = from.plus(offset * TimeSlot.QUANTIZATION_MINUTES, MINUTES);
                    return new TimeSlot(start);
                })
                .collect(Collectors.toList());
        return timeSlots;
    }

    private static Instant quantize(Instant instant) {
        int quantization = TimeSlot.QUANTIZATION_MINUTES;
        int minuteOfHour = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).getMinute(); // TODO: hacky, fix this
        int offsetAmount = minuteOfHour % TimeSlot.QUANTIZATION_MINUTES;
        int threshold = quantization / 2;
        if (offsetAmount <= threshold) {
            return instant.minus(offsetAmount, MINUTES);
        } else {
            int advanceAmount = quantization - offsetAmount;
            return instant.plus(advanceAmount, MINUTES);
        }
    }

}
