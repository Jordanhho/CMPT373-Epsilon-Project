package email;

import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;

import javax.inject.Inject;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MailerService {

    @Inject
    private MailerClient mailerClient;

    private String formatScheduleList(ArrayList<String> scheduleList) {

        StringBuilder stringBuilder = new StringBuilder();

        for (String schedule : scheduleList) {
            stringBuilder.append(schedule)
                    .append(System.getProperty("line.separator"));
        }

        return stringBuilder.toString();
    }

    private String getStartOfNextWeek() {
        Calendar date = Calendar.getInstance();
        while (date.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            date.add(Calendar.DATE, 1);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(date.getTime());
    }

    private String formatScheduleHoursList(ArrayList<Long> scheduleHoursList) {

        long totalHours = 0;

        for (Long hours : scheduleHoursList) {
            totalHours += hours;
        }

        return Long.toString(totalHours);
    }


    private String setScheduleReminderBody(String userFirstName,
                                           String userLastName,
                                           Integer totalNumberOfShifts,
                                           ArrayList<String> scheduleList,
                                           ArrayList<Long> scheduleHoursList) {

        final ArrayList<String> scheduleListCopy = new ArrayList<>(scheduleList);

        Object[] args = {userFirstName,                         // {0}
                userLastName,                                   // {1}
                getStartOfNextWeek(),                           // {2}
                formatScheduleList(scheduleListCopy),           // {3}
                formatScheduleHoursList(scheduleHoursList),     // {4}
                totalNumberOfShifts};                           // {5}

        MessageFormat messageFormat =
                new MessageFormat("Hello {0} {1}," +
                        "\n" +
                        "\n" +
                        "Here is your schedule for the week of {2}:" +
                        "\n" +
                        "\n" +
                        "{3}" +
                        "\n" +
                        "You have a total of {4} hour(s) and {5} shift(s)." +
                        "\n" +
                        "\n" +
                        "If you have any questions about your schedule, please contact your team lead " +
                        "by email, as soon as possible." +
                        "\n" +
                        "\n" +
                        "Note: This email account is used for information distribution only. " +
                        "No replies nor email should be sent directly to this account."
                );

        return messageFormat.format(args);

    }


    public void sendScheduleReminderEmail(String firstName,
                                          String lastName,
                                          Integer totalNumberOfShifts,
                                          ArrayList<String> scheduleList,
                                          ArrayList<Long> scheduleHoursList) {

        Email email = new Email()
                .setSubject("SSEP - Your next week's schedule")
                .setFrom("")
                .addTo("")
                .setBodyText(setScheduleReminderBody(firstName,
                        lastName,
                        totalNumberOfShifts,
                        scheduleList,
                        scheduleHoursList));

        mailerClient.send(email);
    }
}
