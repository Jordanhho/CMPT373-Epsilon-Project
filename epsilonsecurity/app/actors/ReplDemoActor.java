package actors;

import akka.actor.AbstractActor;
import akka.actor.Actor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.stream.Materializer;
import play.libs.akka.InjectedActorSupport;
import static akka.pattern.PatternsCS.ask;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class ReplDemoActor extends AbstractActor implements InjectedActorSupport {

    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    public static class Create {
        final String id;

        public Create(String id) {
            this.id = id;
        }
    }


    @Inject
    public ReplDemoActor(String id, Materializer mat) {

    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Create.class, create -> {

                    sender().tell("ok", self());
                })
                .matchEquals("ping", ping -> {
                    logger.info("Received ping");
                    sender().tell("pong", self());
                })
                .matchAny(any -> {
                    logger.info("Received unhandled message {}", any);
                })
                .build();
    }

    public interface Factory {
        Actor create(String id);
    }
}
