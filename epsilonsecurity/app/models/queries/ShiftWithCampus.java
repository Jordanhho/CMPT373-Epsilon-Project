package models.queries;

import models.databaseModel.scheduling.DbShift;
import models.databaseModel.scheduling.DbShiftType;
import models.databaseModel.scheduling.DbTeam;

/**
 * Java Object for frontend's representation of a shift
 */
public class ShiftWithCampus {
    private Integer id;
    private String title;
    private String timeStart;
    private String timeEnd;
    private String campus;
    private String desc;
    private boolean wasPresent;

    public ShiftWithCampus(DbShift shift, DbTeam team, DbShiftType shiftType) {
        this.id = shift.getId();
        this.title = shiftType.getName();
        this.timeStart = TimeUtil.convertEpochSecondsToIsoUtcTime(shift.getTimeStart());
        this.timeEnd = TimeUtil.convertEpochSecondsToIsoUtcTime(shift.getTimeEnd());
        this.campus = team.getName();
        this.desc = shift.getDescription();
        this.wasPresent = shift.isWasPresent();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getCampus() {
        return campus;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isWasPresent() {
        return wasPresent;
    }
}
