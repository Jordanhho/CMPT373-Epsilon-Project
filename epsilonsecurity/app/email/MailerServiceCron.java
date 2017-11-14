package email;

import akka.actor.ActorSystem;
import models.databaseModel.helpers.*;
import models.databaseModel.scheduling.*;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class MailerServiceCron {

    private final ActorSystem actorSystem;
    private final ExecutionContext executionContext;
    private final MailerService mailerService;

    private static final int NUMBER_OF_DAYS = 6;
    private static final int NUMBER_OF_SECONDS_DELAY = 10;

    @Inject
    public MailerServiceCron(ActorSystem actorSystem,
                             ExecutionContext executionContext,
                             MailerService mailerService) {

        this.actorSystem = actorSystem;
        this.executionContext = executionContext;
        this.mailerService = mailerService;

        this.initialize();
    }


    public void initScheduleReminderEmailCron(String firstName,
                                              String lastName,
                                              Integer totalNumberOfShifts,
                                              ArrayList<String> scheduleList,
                                              ArrayList<Long> scheduleHoursList) {

        final ArrayList<String> scheduleListCopy = new ArrayList<>(scheduleList);
        final ArrayList<Long> scheduleHoursListCopy = new ArrayList<>(scheduleHoursList);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        this.actorSystem.scheduler().schedule(
                Duration.create(NUMBER_OF_SECONDS_DELAY, TimeUnit.SECONDS), // initialDelay
                Duration.create(NUMBER_OF_DAYS, TimeUnit.DAYS), // interval
                () -> mailerService.sendScheduleReminderEmail(firstName,
                        lastName,
                        totalNumberOfShifts,
                        scheduleListCopy,
                        scheduleHoursListCopy),
                this.executionContext
        );
    }


    public void initialize() {

        Integer totalNumberOfShifts = 0;
        String formattedShiftStartDate;
        String formattedShiftEndDate;
        ArrayList<String> scheduleList = new ArrayList<>();
        ArrayList<Long> scheduleHoursList = new ArrayList<>();
        String teamName = "";
        Date shiftStartDate;
        Date shiftEndDate;
        TimeZone timeZone;
        long shiftDurationInMilli;
        long shiftDurationInHours;

        // Go through all users in the database
        for (DbUser dbUser : DbUserHelper.readAllDbUsers()) {

            // Go through all the teams that a user is in
            for (DbUserTeam dbUserTeam : DbUserTeamHelper.readAllDbUserTeamsByUserId(dbUser.getId())) {

                // Get the Team name based on it
                if (DbTeamHelper.readDbTeamById(dbUserTeam.getTeamId()) != null) {
                    teamName = DbTeamHelper.readDbTeamById(dbUserTeam.getTeamId()).getName();
                }

                // Go through all the user shifts based on the user's team id
                for (DbUserShift dbUserShift : DbUserShiftHelper.readAllDbUserShiftByUserId(dbUserTeam.getId())) {

                    // Increment shift counter for the user
                    totalNumberOfShifts++;

                    // Go through all the shifts based on the user's shift id
                    for (DbShift dbShift : DbShiftHelper.readAllDbShiftByShiftId(dbUserShift.getShiftId())) {

                        // Date takes in milliseconds, but shift time is in unix time, seconds.
                        shiftStartDate = new Date(dbShift.getTimeStart() * 1000L);
                        shiftEndDate = new Date(dbShift.getTimeEnd() * 1000L);

                        // Get amount of hours worked per shift
                        shiftDurationInMilli = shiftEndDate.getTime() - shiftStartDate.getTime();
                        shiftDurationInHours = TimeUnit.MILLISECONDS.toHours(shiftDurationInMilli);

                        // Format the start and end dates
                        SimpleDateFormat startDateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                        SimpleDateFormat endDateFormatter = new SimpleDateFormat("HH:mm");

                        // Set the timezone of the dates
                        timeZone = TimeZone.getTimeZone("PST");
                        startDateFormatter.setTimeZone(timeZone);
                        endDateFormatter.setTimeZone(timeZone);

                        formattedShiftStartDate = startDateFormatter.format(shiftStartDate);
                        formattedShiftEndDate = endDateFormatter.format(shiftEndDate);

                        // Add hours worked to list
                        scheduleHoursList.add(shiftDurationInHours);

                        for (DbShiftType dbShiftType : DbShiftTypeHelper.readAllDbShiftTypeById(dbShift.getShiftTypeId())) {

                            scheduleList.add(formattedShiftStartDate + " - " + formattedShiftEndDate + ", " + dbShiftType.getName() + " - " + teamName);
                        }
                    }
                }
            }

            initScheduleReminderEmailCron(dbUser.getFirstName(),
                    dbUser.getLastName(),
                    totalNumberOfShifts,
                    scheduleList,
                    scheduleHoursList);

            // Reset the counters and lists for the new user
            totalNumberOfShifts = 0;
            scheduleList.clear();
            scheduleHoursList.clear();
        }

    }
}
