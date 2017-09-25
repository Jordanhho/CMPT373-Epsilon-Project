package models.schedule;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TimeSlotFactory {

    public static TimeSlot newTimeSlotFrom(DateTime from) {
        return new TimeSlot(quantize(from));
    }

    // TODO: assertions, e.g. to > from and count > 0

    public static List<TimeSlot> newTimeSlots(DateTime from, DateTime to) {
        DateTime quantizedStart = quantize(from);
        DateTime quantizedEnd = quantize(to);
        Duration duration = new Duration(quantizedStart, quantizedEnd);
        int minutesBetween = (int) duration.getStandardMinutes();
        int count = minutesBetween % TimeSlot.QUANTIZATION_MINUTES;
        return newTimeSlots(quantizedStart, count);
    }

    public static List<TimeSlot> newTimeSlots(DateTime from, int count) {
        List<TimeSlot> timeSlots = IntStream.range(0, count)
                .mapToObj( offset -> {
                    DateTime start = from.plusMinutes(offset * TimeSlot.QUANTIZATION_MINUTES);
                    return new TimeSlot(start);
                })
                .collect(Collectors.toList());
        return timeSlots;
    }

    private static DateTime quantize(DateTime dateTime) {
        int quantization = TimeSlot.QUANTIZATION_MINUTES;
        int minuteOfDay = dateTime.minuteOfHour().get();
        int offsetAmount = minuteOfDay % TimeSlot.QUANTIZATION_MINUTES;
        int threshold = quantization / 2;
        if (offsetAmount <= threshold) {
            return dateTime.minusMinutes(offsetAmount);
        } else {
            int advanceAmount = quantization - offsetAmount;
            return dateTime.plusMinutes(advanceAmount);
        }
    }

}
