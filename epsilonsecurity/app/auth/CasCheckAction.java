//package auth;
//
//import play.mvc.Http;
//import play.mvc.Result;
//import play.mvc.Security;
//
//public class CasCheckAction extends Security.Authenticator {
//
//    @Override
//    public String getUsername(Http.Context ctx) {
//        return ctx.session().get("user_token");
//    }
//
//    @Override
//    public Result onUnauthorized(Http.Context ctx) {
//    }
//}
