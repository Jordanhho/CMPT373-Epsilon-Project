package models;

import java.time.Duration;
import java.util.Date;

public class TimeBlock {
    public Date startTime;
    public Date endTime;

    public TimeBlock(Date startTime, Date endTime) {

    }


    // TODO: requires initializer

    public Duration getDuration() {
        return Duration.between(startTime.toInstant(), endTime.toInstant());
    }
}
