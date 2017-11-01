//package auth;
//
//import akka.stream.Materializer;
//import play.http.HttpFilters;
//import play.mvc.*;
//
//import javax.inject.Inject;
//import java.util.List;
//import java.util.concurrent.CompletionStage;
//import java.util.function.Function;
//
//public class CasFilter extends Filter {
//
//    @Inject
//    public CasFilter(Materializer mat) {
//        super(mat);
//    }
//
//    @Override
//    public CompletionStage<Result> apply(Function<Http.RequestHeader, CompletionStage<Result>> next, Http.RequestHeader rh) {
//        return null;
//    }
//
//}
