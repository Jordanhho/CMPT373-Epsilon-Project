package models.databaseModel.scheduling;

/**
 * Java Object for creating a DbUserShift Form
 */
public class DbUserShiftForm {

    private String sfuEmail;
    private Integer timeStart;
    private Integer timeEnd;

    public void setSfuEmail(String sfuEmail) {
        this.sfuEmail = sfuEmail;
    }

    public String getSfuEmail() {
        return sfuEmail;
    }

    public void setTimeStart(Integer timeStart) {
        this.timeStart = timeStart;
    }

    public Integer getTimeStart() {
        return timeStart;
    }

    public void setTimeEnd(Integer timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Integer getTimeEnd() {
        return timeEnd;
    }
}