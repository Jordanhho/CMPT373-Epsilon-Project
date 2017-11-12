package controllers;

import email.MailerServiceCron;
import models.databaseModel.helpers.*;
import models.databaseModel.scheduling.*;
import models.queries.ScheduleUtil;
import models.queries.ShiftWithCampus;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class UserShiftController extends Controller {

    private final FormFactory formFactory;
    private final MailerServiceCron mailerServiceCron;

    @Inject
    UserShiftController(FormFactory formFactory, MailerServiceCron mailerServiceCron) {
        this.formFactory = formFactory;
        this.mailerServiceCron = mailerServiceCron;
    }

    private DbUserShift getDbUserShiftFromForm() {

        // From the request, create a form that can handle a DbUserShift object.
        Form<DbUserShift> form = formFactory.form(DbUserShift.class).bindFromRequest();

        // Create a DbUserShift object from the form data.
        DbUserShift dbUserShift = form.get();

        return dbUserShift;
    }

    public Result createUserShift() {

        // Create a DbUserShift object from the form data.
        DbUserShift dbUserShift = getDbUserShiftFromForm();

        // Enter the DbUserShift into the database.
        DbUserShiftHelper.createDbUserShift(dbUserShift);

        return ok();
    }

    public Result createUserShift(Integer userId, Integer shiftId) {
        // TODO: Implement createUserShift() with input parameters

        Integer totalNumberOfShifts = 0;
        String formattedDate;
        ArrayList<String> scheduleList = new ArrayList<>();
        ArrayList<Long> scheduleHoursList = new ArrayList<>();

        for (DbUser dbUser : DbUserHelper.readAllDbUsers()) {

//            System.out.println();
//            System.out.println(dbUser.getFirstName() + " " + dbUser.getLastName() + " " + dbUser.getId());

            for (DbUserTeam dbUserTeam : DbUserTeamHelper.readAllDbUserTeamsByUserId(dbUser.getId())) {

//                System.out.println(dbUserTeam);

                for (DbUserShift dbUserShift : DbUserShiftHelper.readAllDbUserShiftByUserId(dbUserTeam.getId())) {

                    totalNumberOfShifts++;

                    for (DbShift dbShift : DbShiftHelper.readAllDbShiftByShiftId(dbUserShift.getShiftId())) {

//                        System.out.println(dbShift.getShiftTypeId());

//                        System.out.println(dbShift.getTimeStart());
                        Date startDate = new Date(dbShift.getTimeStart() * 1000L);
                        Date endDate = new Date(dbShift.getTimeEnd() * 1000L);

                        long duration = endDate.getTime() - startDate.getTime();
                        long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
                        System.out.println("Duration: " + duration);

                        System.out.println("Diff in hours: " + diffInHours);

                        SimpleDateFormat startDateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                        SimpleDateFormat endDateFormatter = new SimpleDateFormat("HH:mm");

                        TimeZone timeZone = TimeZone.getTimeZone("PST");
                        startDateFormatter.setTimeZone(timeZone);
                        endDateFormatter.setTimeZone(timeZone);

                        formattedDate = startDateFormatter.format(startDate);
//                        System.out.println(formattedDate);

                        String formattedDate2 = endDateFormatter.format(endDate);
//                        System.out.println(formattedDate2);

                        scheduleHoursList.add(diffInHours);

                        for (DbShiftType dbShiftType : DbShiftTypeHelper.readAllDbShiftTypeById(dbShift.getShiftTypeId())) {

//                            System.out.println("ShiftType: " + dbShiftType.getName());
//                            System.out.println(formattedDate + " - " + formattedDate2 + ", " + dbShiftType.getName());
                            scheduleList.add(formattedDate + " - " + formattedDate2 + ", " + dbShiftType.getName());

                        }

                    }
                }
            }

//            System.out.println(scheduleList);

            mailerServiceCron.initScheduleReminderEmailCron(dbUser.getFirstName(),
                    dbUser.getLastName(),
                    totalNumberOfShifts,
                    scheduleList,
                    scheduleHoursList);

//            System.out.println("Total Number of shifts: " + totalNumberOfShifts);
            totalNumberOfShifts = 0;
            scheduleList.clear();
            scheduleHoursList.clear();
        }

        return ok();
    }

    public Result retrieveUserShift(Integer userId) {
        List<DbUserShift> dbUserShiftList = DbUserShiftHelper.readAllDbUserShiftByUserId(userId);

        return ok(Json.toJson(dbUserShiftList));
    }

    public Result retrieveShiftsByUserId(Integer userId) {
        List<ShiftWithCampus> shiftsWithCampusList = ScheduleUtil.getShiftsWithCampusByUserId(userId);
        return ok(Json.toJson(shiftsWithCampusList));
    }

    public Result deleteUserShifts(Integer shiftId) {
        List<DbUserShift> dbUserShiftsToDelete = DbUserShiftHelper
                .readDbUserShiftByShiftId(shiftId);

        DbUserShiftHelper.deleteDbUserShifts(dbUserShiftsToDelete);
        return ok();
    }
}