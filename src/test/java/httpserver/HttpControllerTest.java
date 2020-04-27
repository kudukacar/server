package httpserver;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class HttpControllerTest {

    @Test
    void ItDirectsARouteableHttpRequest() {
        Resource getWithNoBody = new GetWithNoBody();
        String path = "/hello_world";
        List<Route> routes = new ArrayList<Route>(Collections.singletonList(new Route(path, getWithNoBody)));
        Resource nonRouteable = new NonRouteable();
        HttpController httpController = new HttpController(routes, nonRouteable);
        HttpResponse expectedResponse = new HttpResponse();
        expectedResponse.responseLine += "200 Ok";

        assertThat(expectedResponse, samePropertyValuesAs(httpController.control(path)));
    }

    @Test
    void ItDirectsANonRouteableHttpRequest() {
        Resource nonRouteable = new NonRouteable();
        Resource getWithNoBody = new GetWithNoBody();
        String path = "/goodbye";
        List<Route> routes = new ArrayList<Route>(Collections.singletonList(new Route(path, getWithNoBody)));
        HttpController httpController = new HttpController(routes, nonRouteable);
        HttpResponse expectedResponse = new HttpResponse();
        expectedResponse.responseLine += "405 Method Not Allowed";
        expectedResponse.headers = new ArrayList<String>(Arrays.asList("Allow: HEAD, OPTIONS"));

        assertThat(expectedResponse, samePropertyValuesAs(httpController.control(path)));
    }

    private class GetWithNoBody implements Resource {
        public HttpResponse act() {
            HttpResponse response = new HttpResponse();
            response.responseLine += "200 Ok";
            return response;
        }
    }

    private class NonRouteable implements Resource {
        public HttpResponse act() {
            HttpResponse response = new HttpResponse();
            response.responseLine += "405 Method Not Allowed";
            response.headers = new ArrayList<String>(Arrays.asList("Allow: HEAD, OPTIONS"));
            return response;
        }
    }
}