package models.queries;

import models.databaseModel.scheduling.DbShift;
import models.databaseModel.scheduling.DbShiftType;
import models.databaseModel.scheduling.DbTeam;

/**
 * Java Object for frontend's representation just a shift
 */
public class ShiftWithCampus {
    private Integer id;
    private String title;
    private String start;
    private String end;
    private String campus;
    private String description;
    private boolean wasPresent;

    public ShiftWithCampus(DbShift shift, DbTeam team, DbShiftType shiftType) {
        this.id = shift.getId();
        this.title = shiftType.getName();
        this.start = TimeUtil.convertEpochSecondsToIsoUtcTime(shift.getTimeStart());
        this.end = TimeUtil.convertEpochSecondsToIsoUtcTime(shift.getTimeEnd());
        this.campus = team.getName();
        this.description = shift.getDescription();
        this.wasPresent = shift.isWasPresent();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getCampus() {
        return campus;
    }

    public String getDescription() {
        return description;
    }

    public boolean isWasPresent() {
        return wasPresent;
    }
}
