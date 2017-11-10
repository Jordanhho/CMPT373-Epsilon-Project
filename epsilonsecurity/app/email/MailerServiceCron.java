package email;

import akka.actor.ActorSystem;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class MailerServiceCron {

    private final ActorSystem actorSystem;
    private final ExecutionContext executionContext;
    private final MailerService mailerService;

    @Inject
    public MailerServiceCron(ActorSystem actorSystem, ExecutionContext executionContext, MailerService mailerService) {
        this.actorSystem = actorSystem;
        this.executionContext = executionContext;
        this.mailerService = mailerService;
    }

    public void initialize() {
        System.out.println("Email has been sent");
        this.actorSystem.scheduler().scheduleOnce(
                Duration.create(3, TimeUnit.SECONDS), // initialDelay
                () -> mailerService.sendEmail(),
                this.executionContext
        );
    }
}
