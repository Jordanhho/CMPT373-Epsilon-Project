package export;

import models.schedule.Schedule;
import models.schedule.TimeBlock;
import org.junit.Assert;
import org.junit.Test;
import store.UserId;

import java.time.Instant;

public class PDFExportableScheduleTest  {

    @Test
    public void testOutput() {
        Schedule schedule = new Schedule();
        TimeBlock now = TimeBlock.single(Instant.now());
        schedule.createTimeBlock(now);
        schedule.assignUserToTimeBlock(new UserId(0), now);
        PDFExportableSchedule scheduleExporter = new PDFExportableSchedule(schedule);
        StringBuilder result = scheduleExporter.constructLaTeXRepresentation();
        Assert.assertNotNull(result);
        System.out.print(result.toString());
    }
}
