package httpserver;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
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

        Map<String, String> request = new HashMap<>();
        request.put("path", path);
        request.put("method", method);

        HttpResponse expectedResponse = new HttpResponse.HttpResponseBuilder("200 Ok").build();
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

        Map<String, String> request = new HashMap<>();
        request.put("path", path);
        request.put("method", "HEAD");

        HttpResponse expectedResponse = new HttpResponse.HttpResponseBuilder("405 Method Not Allowed")
                .headers(new ArrayList<String>(Collections.singletonList("Allow: HEAD, OPTIONS")))
                .build();

        HttpRouter httpRouter = new HttpRouter(routes, new NonRouteable());

        assertThat(expectedResponse, samePropertyValuesAs(httpRouter.route(request)));
    }

    private static class GetWithNoBody implements Action {
        public HttpResponse act() {
            return new HttpResponse.HttpResponseBuilder("200 Ok").build();
        }
    }

    private static class NonRouteable implements Action {
        public HttpResponse act() {
            return new HttpResponse.HttpResponseBuilder("405 Method Not Allowed")
                    .headers(new ArrayList<String>(Collections.singletonList("Allow: HEAD, OPTIONS")))
                    .build();
        }
    }
}