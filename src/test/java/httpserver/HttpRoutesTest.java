package httpserver;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void itReturnsAnOptionalActionIfAvailable() {
        Action action = new SimpleGet();
        String path = "/simple_get";
        String GET = "GET";
        String HEAD = "HEAD";
        HttpRoutes routes = new HttpRoutes.Builder()
                .addRoute(path, GET, action)
                .addRoute(path, HEAD, action)
                .build();

        assertTrue(routes.getAction(path, GET).isPresent());
    }

    @Test
    void itReturnsAnEmptyOptionalIfActionNotAvailable() {
        Action action = new SimpleGet();
        String path = "/simple_get";
        String GET = "GET";
        String HEAD = "HEAD";
        HttpRoutes routes = new HttpRoutes.Builder()
                .addRoute(path, GET, action)
                .addRoute(path, HEAD, action)
                .build();

        assertFalse(routes.getAction("/simple", GET).isPresent());
    }

    private static class SimpleGet implements Action {
        @Override
        public HttpResponse act() {
            return null;
        }
    }
}