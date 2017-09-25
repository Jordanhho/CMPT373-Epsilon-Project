package models.schedule;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TimeSlotFactoryTest {

    private static DateTime getNoon() {
        return new DateTime(2017, 1, 1, 12, 0, 0);
    }

    @Test
    public void testExactQuantization() {
        DateTime noon = getNoon();
        TimeSlot noonTimeSlot = TimeSlotFactory.newTimeSlotFrom(noon);
        assertEquals(noonTimeSlot.getStart(), noon);
    }

    @Test
    public void testLowerQuantization() {
        DateTime noonAndABit = getNoon().plusMinutes(7);
        DateTime noonAndALot = getNoon().plusMinutes(8);
        TimeSlot noonTimeSlot = TimeSlotFactory.newTimeSlotFrom(getNoon());
        TimeSlot noonAndABitTimeSlot = TimeSlotFactory.newTimeSlotFrom(noonAndABit);
        TimeSlot noonAndALotTimeSlot = TimeSlotFactory.newTimeSlotFrom(noonAndALot);
        assertEquals(noonTimeSlot.getStart(), noonAndABitTimeSlot.getStart());
        assertNotEquals(noonTimeSlot.getStart(), noonAndALotTimeSlot.getStart());
        assertEquals(noonTimeSlot, noonAndABitTimeSlot);
    }

    @Test
    public void testUpperQuantization() {
        DateTime noon = getNoon();
        DateTime noonAndALot = getNoon().plusMinutes(8);
        TimeSlot noonTimeSlot = TimeSlotFactory.newTimeSlotFrom(noon);
        TimeSlot noonAndALotTimeSlot = TimeSlotFactory.newTimeSlotFrom(noonAndALot);
        assertNotEquals(noonTimeSlot.getStart(), noonAndALotTimeSlot.getStart());
        assertNotEquals(noonTimeSlot, noonAndALotTimeSlot);
    }

}
