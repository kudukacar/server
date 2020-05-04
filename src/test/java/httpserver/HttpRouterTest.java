package httpserver;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class HttpRouterTest {

    @Test
    void ItDirectsARouteableHttpRequest() {
        String path = "/simple_get";
        String method = "GET";

        HttpRoutes routes = new HttpRoutes.Builder()
                .addRoute(path, method, new GetWithNoBody())
                .build();

        Optional<HttpRequest> request = Optional.of(new HttpRequest(method, path));

        HttpResponse expectedResponse = new HttpResponse.Builder()
                .status(HttpStatus.OK)
                .build();
        HttpRouter httpRouter = new HttpRouter(routes, new NonRouteable(), new NonRouteable());

        assertThat(expectedResponse, samePropertyValuesAs(httpRouter.route(request)));
    }

    @Test
    void ItDirectsANonRouteableHttpRequest() {
        String path = "/simple_get";

        HttpRoutes routes = new HttpRoutes.Builder()
                .addRoute(path, "GET", new GetWithNoBody())
                .build();

        Optional<HttpRequest> request = Optional.of(new HttpRequest("HEAD", path));

        HttpResponse expectedResponse = new HttpResponse.Builder()
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .addHeader("Allow: HEAD, OPTIONS")
                .build();

        HttpRouter httpRouter = new HttpRouter(routes, new NonRouteable(), new NonRouteable());

        assertThat(expectedResponse, samePropertyValuesAs(httpRouter.route(request)));
    }

    @Test
    void ItDirectsABadHttpRequest() {
        HttpRoutes routes = new HttpRoutes.Builder()
                .addRoute("/simple_get", "GET", new GetWithNoBody())
                .build();

        HttpResponse expectedResponse = new HttpResponse.Builder()
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .addHeader("Allow: HEAD, OPTIONS")
                .build();

        HttpRouter httpRouter = new HttpRouter(routes, new NonRouteable(), new NonRouteable());

        assertThat(expectedResponse, samePropertyValuesAs(httpRouter.route(Optional.empty())));
    }

    private static class GetWithNoBody implements Action {
        public HttpResponse act() {
            return new HttpResponse.Builder()
                    .status(HttpStatus.OK)
                    .build();
        }
    }

    private static class NonRouteable implements Action {
        public HttpResponse act() {
            return new HttpResponse.Builder()
                    .status(HttpStatus.METHOD_NOT_ALLOWED)
                    .addHeader("Allow: HEAD, OPTIONS")
                    .build();
        }
    }
}