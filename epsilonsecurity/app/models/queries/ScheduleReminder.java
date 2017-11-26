package models.queries;

public class ScheduleReminder {

    private String shiftName;
    private String shiftStartTime;
    private String shiftEndTime;
    private String shiftDate;

    public ScheduleReminder(String shiftName,
                            String shiftStartTime,
                            String shiftEndTime,
                            String shiftDate) {
        this.shiftName = shiftName;
        this.shiftStartTime = shiftStartTime;
        this.shiftEndTime = shiftEndTime;
        this.shiftDate = shiftDate;
    }


    public String getShiftName() {
        return shiftName;
    }


    public String getShiftStartTime() {
        return shiftStartTime;
    }


    public String getShiftEndTime() {
        return shiftEndTime;
    }


    public String getShiftDate() {
        return shiftDate;
    }
}
