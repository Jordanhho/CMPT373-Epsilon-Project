package models.queries;

import models.databaseModel.helpers.*;
import models.databaseModel.scheduling.*;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ScheduleReminder {

    private String shiftName;
    private String shiftStartTime;
    private String shiftEndTime;
    private String shiftDate;
    private String shiftTeamName;
    private String daysTilShift;

    public ScheduleReminder(String shiftName,
                            String shiftStartTime,
                            String shiftEndTime,
                            String shiftDate,
                            String shiftTeamName,
                            String daysTilShift) {
        this.shiftName = shiftName;
        this.shiftStartTime = shiftStartTime;
        this.shiftEndTime = shiftEndTime;
        this.shiftDate = shiftDate;
        this.shiftTeamName = shiftTeamName;
        this.daysTilShift = daysTilShift;
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


    public String getShiftTeamName() {
        return shiftTeamName;
    }


    public String getDaysTilShift() {
        return daysTilShift;
    }


    private static boolean isShiftOver(DbShift dbShift) {
        // DbShift timeEnd is measured in seconds and system time is in milliseconds
        return (dbShift.getTimeEnd() * 1000L) < System.currentTimeMillis();
    }


    private static String getDaysBetweenTodayAndShift(Date shiftStartTime) {
        Date today = new Date(System.currentTimeMillis());

        Integer daysTil = Days.daysBetween(new DateTime(today), new DateTime(shiftStartTime)).getDays();
//        System.out.println("EEEEEEEEEEE");
//        System.out.println(Integer.toString(days));
//        System.out.println("EEEEEEEEEEE");

        return Integer.toString(daysTil);
    }


    public static List<ScheduleReminder> getScheduleRemindersByUserId(Integer userId) {

        List<ScheduleReminder> scheduleReminderList = new ArrayList<>();

        String shiftName;
        String formattedShiftStartTime;
        String formattedShiftEndTime;
        String formattedShiftStartDate;
        String shiftTeamName;
        String daysTilShift;

        DbTeam dbTeam;

        Date shiftStartTime;
        Date shiftEndTime;

        SimpleDateFormat startTimeFormatter;
        SimpleDateFormat endTimeFormatter;
        SimpleDateFormat startDateFormatter;

        TimeZone timeZone;

        // Iterate through all teams based on the user's id.
        for (DbUserTeam dbUserTeam : DbUserTeamHelper.readAllDbUserTeamsByUserId(userId)) {

            // Get team name a user is on.
            dbTeam = DbTeamHelper.readDbTeamById(dbUserTeam.getTeamId());
            shiftTeamName = dbTeam.getName();

            // Iterate through all user shifts based on the user's DbTeam id.
            for (DbUserShift dbUserShift : DbUserShiftHelper.readDbUserShiftByUserTeamId(dbUserTeam.getId())) {

                // Iterate through all the shifts based on the user's DbShift id.
                for (DbShift dbShift : DbShiftHelper.readAllDbShiftByShiftId(dbUserShift.getShiftId())) {

                    // Add shifts which are not over yet to the reminders
                    if (isShiftOver(dbShift)) {

                        // Create dates from the shift start and end dates.
                        shiftStartTime = new Date(dbShift.getTimeStart() * 1000L);
                        shiftEndTime = new Date(dbShift.getTimeEnd() * 1000L);

                        // Choose date format of shift start and end dates.
                        startTimeFormatter = new SimpleDateFormat("HH:mm");
                        endTimeFormatter = new SimpleDateFormat("HH:mm");
                        startDateFormatter = new SimpleDateFormat("MM/dd/yy");

                        // Set the correct time zone.
                        timeZone = TimeZone.getTimeZone("PST");
                        startTimeFormatter.setTimeZone(timeZone);
                        endTimeFormatter.setTimeZone(timeZone);
                        startDateFormatter.setTimeZone(timeZone);

                        // Format shift dates into strings.
                        formattedShiftStartTime = startTimeFormatter.format(shiftStartTime);
                        formattedShiftEndTime = endTimeFormatter.format(shiftEndTime);
                        formattedShiftStartDate = startDateFormatter.format(shiftStartTime);

                        for (DbShiftType dbShiftType : DbShiftTypeHelper
                                .readAllDbShiftTypeById(dbShift.getShiftTypeId())) {

                            shiftName = dbShiftType.getName();
                            daysTilShift = getDaysBetweenTodayAndShift(shiftStartTime);

                            scheduleReminderList.add(new ScheduleReminder(shiftName,
                                    formattedShiftStartTime,
                                    formattedShiftEndTime,
                                    formattedShiftStartDate,
                                    shiftTeamName,
                                    daysTilShift));
                        }
                    }
                }
            }
        }

        return scheduleReminderList;
    }
}
