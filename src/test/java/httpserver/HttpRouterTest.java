package httpserver;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class HttpRouterTest {

    @Test
    void ItDirectsARouteableHttpRequest() {
        String path = "/simple_get";
        String method = "GET";

        Map<String, Map<String, Action>> routes =  new HashMap<>();
        routes.put("/simple_get", Stream.of(new Object[][] {
                {method, new GetWithNoBody()},
        }).collect(Collectors.toMap(entries -> (String) entries[0], entries -> (Action) entries[1])));

        HttpRequest request = new HttpRequest(method, path);

        HttpResponse expectedResponse = new HttpResponse.Builder("200 Ok").build();
        HttpRouter httpRouter = new HttpRouter(routes, new NonRouteable());

        assertThat(expectedResponse, samePropertyValuesAs(httpRouter.route(request)));
    }

    @Test
    void ItDirectsANonRouteableHttpRequest() {
        String path = "/simple_get";

        Map<String, Map<String, Action>> routes =  new HashMap<>();
        routes.put("/simple_get", Stream.of(new Object[][] {
                {"GET", new GetWithNoBody()},
        }).collect(Collectors.toMap(entries -> (String) entries[0], entries -> (Action) entries[1])));

        HttpRequest request = new HttpRequest("HEAD", path);

        HttpResponse expectedResponse = new HttpResponse.Builder("405 Method Not Allowed")
                .addHeader("Allow: HEAD, OPTIONS")
                .build();

        HttpRouter httpRouter = new HttpRouter(routes, new NonRouteable());

        assertThat(expectedResponse, samePropertyValuesAs(httpRouter.route(request)));
    }

    private static class GetWithNoBody implements Action {
        public HttpResponse act() {
            return new HttpResponse.Builder("200 Ok").build();
        }
    }

    private static class NonRouteable implements Action {
        public HttpResponse act() {
            return new HttpResponse.Builder("405 Method Not Allowed")
                    .addHeader("Allow: HEAD, OPTIONS")
                    .build();
        }
    }
}