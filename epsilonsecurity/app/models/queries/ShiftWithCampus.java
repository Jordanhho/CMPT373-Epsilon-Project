package models.queries;

import models.databaseModel.scheduling.DbShift;
import models.databaseModel.scheduling.DbTeam;

public class ShiftWithCampus {
    private Integer id;
    private Integer shiftTypeId;
    private Long timeStart;
    private Long timeEnd;
    private String campus;
    private boolean wasPresent;

    public ShiftWithCampus(DbShift shift, DbTeam team) {
        this.id = shift.getId();
        this.shiftTypeId = shift.getShiftTypeId();
        this.timeStart = shift.getTimeStart();
        this.timeEnd = shift.getTimeEnd();
        this.campus = team.getName();
        this.wasPresent = shift.isWasPresent();
    }

    public Integer getId() {
        return id;
    }

    public Integer getShiftTypeId() {
        return shiftTypeId;
    }

    public Long getTimeStart() {
        return timeStart;
    }

    public Long getTimeEnd() {
        return timeEnd;
    }

    public String getCampus() {
        return campus;
    }

    public boolean isWasPresent() {
        return wasPresent;
    }
}
