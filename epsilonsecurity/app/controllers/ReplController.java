package controllers;

import actors.ReplDemoActor;
import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.*;
import akka.stream.javadsl.Flow;
import akka.util.Timeout;
import org.slf4j.Logger;
import play.libs.F;
import play.mvc.*;
import play.mvc.WebSocket;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;
import static akka.pattern.PatternsCS.ask;

public class ReplController {

    private final ActorRef replDemoActor;
    private final Logger logger = org.slf4j.LoggerFactory.getLogger("controllers.HomeController");


    @Inject
    public ReplController(@Named("replDemoActor") ActorRef replDemoActor) {
        this.replDemoActor = replDemoActor;
    }

    public Result index() {
        return ok(views.html.repl.render(request()));
    }

    // creates and configures web socket
    public WebSocket ws() {
        return WebSocket.Text.acceptOrResult(request -> {
//           if (sameOriginCheck(request)) {
                final CompletionStage<Flow<String, String, NotUsed>> future = wsFutureFlow(request);
                final CompletionStage<F.Either<Result, Flow<String, String, ?>>> stage = future.thenApply(F.Either::Right);
                return stage.exceptionally(this::logException);
//           }
        });
    }

    // future for creating websocket actor
    private CompletionStage<Flow<String, String, NotUsed>> wsFutureFlow(Http.RequestHeader request) {
        long id = request.asScala().id();
        ReplDemoActor.Create create = new ReplDemoActor.Create(Long.toString(id));
        return ask(replDemoActor, create, new Timeout(Duration.create(5, TimeUnit.SECONDS)))
                .thenApply((Object flow) -> {
                    final Flow<String, String, NotUsed> f = (Flow<String, String, NotUsed>) flow;
                    return f.named("websocket");
                });
    }

    private F.Either<Result, Flow<String, String, ?>> logException(Throwable throwable) {
        logger.error("Cannot create websocket", throwable);
        Result result = Results.internalServerError("error");
        return F.Either.Left(result);
    }
}
