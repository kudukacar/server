package httpserver;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class HttpRoutesTest {
    @Test
    void itCreatesAMapofRoutes() {
        Action action = new SimpleGet();
        String path = "/simple_get";
        String GET = "GET";
        String HEAD = "HEAD";
        HttpRoutes routes = new HttpRoutes.Builder()
                .addRoute(path, GET, action)
                .addRoute(path, HEAD, action)
                .build();

        Map<String, Map<String, Action>> expectedRoute = new HashMap<>();
        Map<String, Action> expectedActions = new HashMap<>();
        expectedActions.put(GET, action);
        expectedActions.put(GET, action);
        expectedRoute.put(path, expectedActions);

        assertThat(expectedRoute, samePropertyValuesAs(routes.getRoutes()));
    }

    private static class SimpleGet implements Action {
        @Override
        public HttpResponse act() {
            return null;
        }
    }
}