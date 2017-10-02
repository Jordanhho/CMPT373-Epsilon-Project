package models.schedule;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.HOURS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScheduleTest {

    @Test
    public void testNoShifts() {
        Schedule schedule = new Schedule();
        assertTrue(schedule.getShifts().isEmpty());
        schedule.createTimeBlock(TimeBlock.single(Instant.now()));
        assertTrue(schedule.getShifts().isEmpty());
    }

    @Test
    public void testTimeBlockCreation() {
        final Schedule schedule = new Schedule();

        assertTrue(schedule.getTimeBlocks().isEmpty());

        final TimeBlock startTime = TimeBlock.single(Instant.now());
        schedule.createTimeBlock(startTime);

        assertTimeBlocksInSchedule(1, schedule, startTime);

        final TimeBlock laterTime = TimeBlock.single(Instant.now().plus(1, HOURS));
        schedule.createTimeBlock(laterTime);

        assertTimeBlocksInSchedule(2, schedule, startTime, laterTime);

        boolean didRemove = schedule.removeTimeBlock(startTime);
        assertTrue(didRemove);
        assertTimeBlocksInSchedule(1, schedule, laterTime);
    }

    private void assertTimeBlocksInSchedule(int count, Schedule schedule, TimeBlock... expectedTimeBlocks) {
        final Set<TimeBlock> expectedTimeBlockSet = new HashSet<TimeBlock>(Arrays.asList(expectedTimeBlocks));
        final Set<TimeBlock> actualTimeBlockSet = schedule.getTimeBlocks();
        assertEquals(expectedTimeBlockSet, actualTimeBlockSet);
    }
}
