package models.schedule;

import models.databaseModel.scheduling.DbShiftType;

public class ShiftType {
    private String name;
    private String colorCode;

    public ShiftType(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
    }

    public ShiftType(DbShiftType dbShiftType) {
        new ShiftType(dbShiftType.getName(), dbShiftType.getColorCode());
    }

}
