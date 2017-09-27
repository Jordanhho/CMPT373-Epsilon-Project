package models.schedule;

import org.junit.Test;

import java.time.*;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TimeSlotFactoryTest {

    private static Instant getNoon() {
        LocalDateTime noon = LocalDateTime.of(LocalDate.of(2017, 1, 1),
                                              LocalTime.of(12, 0, 0));
        return noon.toInstant(ZoneOffset.ofHours(0));
    }

    @Test
    public void testExactQuantization() {

        Instant noon = getNoon();
        TimeSlot noonTimeSlot = TimeSlotFactory.newTimeSlotFrom(noon);
        assertEquals(noonTimeSlot.getStart(), noon);
    }

    @Test
    public void testLowerQuantization() {
        Instant noonAndABit = getNoon().plus(7, MINUTES);
        Instant noonAndALot = getNoon().plus(8, MINUTES);
        TimeSlot noonTimeSlot = TimeSlotFactory.newTimeSlotFrom(getNoon());
        TimeSlot noonAndABitTimeSlot = TimeSlotFactory.newTimeSlotFrom(noonAndABit);
        TimeSlot noonAndALotTimeSlot = TimeSlotFactory.newTimeSlotFrom(noonAndALot);
        assertEquals(noonTimeSlot.getStart(), noonAndABitTimeSlot.getStart());
        assertNotEquals(noonTimeSlot.getStart(), noonAndALotTimeSlot.getStart());
        assertEquals(noonTimeSlot, noonAndABitTimeSlot);
    }

    @Test
    public void testUpperQuantization() {
        Instant noon = getNoon();
        Instant noonAndALot = getNoon().plus(8, MINUTES);
        TimeSlot noonTimeSlot = TimeSlotFactory.newTimeSlotFrom(noon);
        TimeSlot noonAndALotTimeSlot = TimeSlotFactory.newTimeSlotFrom(noonAndALot);
        assertNotEquals(noonTimeSlot.getStart(), noonAndALotTimeSlot.getStart());
        assertNotEquals(noonTimeSlot, noonAndALotTimeSlot);
    }

}
