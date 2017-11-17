package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void home_controller_returns_spa() {
        Http.RequestBuilder req = Helpers.fakeRequest(controllers.routes.HomeController.index("dummy"));
        Result result = route(Helpers.fakeApplication(), req);
        assertEquals(OK, result.status());
    }

    @Test
    public void backend_returns_spa() {
        // we use "GET /dummy" and not "GET /" because the later can return status=303
        // when a redirect to CAS is done.
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/dummy");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

}
