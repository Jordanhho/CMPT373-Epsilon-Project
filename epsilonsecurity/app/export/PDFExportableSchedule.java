package export;

import models.schedule.Schedule;

import java.time.Instant;

public class PDFExportableSchedule implements PDFExportable {

    private Instant initTime;
    private Schedule schedule;

    public PDFExportableSchedule(Schedule schedule) {
        this.initTime = Instant.now();
        this.schedule = schedule;
    }

    @Override
    public String uniqueIdentifier() {
        return initTime.toString();
    }

    @Override
    public String constructLaTeXRepresentation() {
        // write pre-amble
        // maybe color definitions depending on shifts? Maybe not
        //
        return null;
    }
}
