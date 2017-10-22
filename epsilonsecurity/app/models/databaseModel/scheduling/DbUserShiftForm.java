package models.databaseModel.scheduling;

/**
 * Java Object for creating a DbUserShift Form
 */
public class DbUserShiftForm {

    private String sfuEmail;
    private Long timeStart;
    private Long timeEnd;

    public void setSfuEmail(String sfuEmail) {
        this.sfuEmail = sfuEmail;
    }

    public String getSfuEmail() {
        return sfuEmail;
    }

    public void setTimeStart(Long timeStart) {
        this.timeStart = timeStart;
    }

    public Long getTimeStart() {
        return timeStart;
    }

    public void setTimeEnd(Long timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Long getTimeEnd() {
        return timeEnd;
    }
}