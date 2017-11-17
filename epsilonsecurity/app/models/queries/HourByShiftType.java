package models.queries;

public class HourByShiftType {
    private String shiftTypeName;
    private int shiftTypeId;
    private float hour;

    public HourByShiftType(int shiftTypeId, String shiftTypeName, float hour){
        this.shiftTypeId = shiftTypeId;
        this.shiftTypeName = shiftTypeName;
        this.hour = hour;
    }

    public String getShiftTypeName() {
        return shiftTypeName;
    }

    public int getShiftTypeId() {
        return shiftTypeId;
    }

    public float getHour() {
        return hour;
    }


    public void setHour(float hour){
        this.hour = hour;
    }

    public void addHour(float hour){
        this.hour = this.hour + hour;
    }
}
