package email;

import akka.actor.ActorSystem;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MailerServiceCron {

    private final ActorSystem actorSystem;
    private final ExecutionContext executionContext;
    private final MailerService mailerService;

    private static final int NUMBER_OF_DAYS = 6;

    @Inject
    public MailerServiceCron(ActorSystem actorSystem,
                             ExecutionContext executionContext,
                             MailerService mailerService) {

        this.actorSystem = actorSystem;
        this.executionContext = executionContext;
        this.mailerService = mailerService;
    }


    public void initScheduleReminderEmailCron(String firstName,
                                              String lastName,
                                              Integer totalNumberOfShifts,
                                              ArrayList<String> scheduleList,
                                              ArrayList<Long> scheduleHoursList) {

        final ArrayList<String> scheduleListCopy = new ArrayList<>(scheduleList);
        final ArrayList<Long> scheduleHoursListCopy = new ArrayList<>(scheduleHoursList);

        this.actorSystem.scheduler().scheduleOnce(
                Duration.create(NUMBER_OF_DAYS, TimeUnit.SECONDS), // initialDelay
                () -> mailerService.sendScheduleReminderEmail(firstName,
                        lastName,
                        totalNumberOfShifts,
                        scheduleListCopy,
                        scheduleHoursListCopy),
                this.executionContext
        );
    }
}
