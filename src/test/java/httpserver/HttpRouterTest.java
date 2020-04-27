package httpserver;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class HttpRouterTest {

    @Test
    void ItDirectsARouteableHttpRequest() {
        String path = "/simple_get";
        HttpRouter httpRouter = new HttpRouter(new GetWithABody(), new GetWithNoBody(), new NonRouteable());
        HttpResponse expectedResponse = new HttpResponse();
        expectedResponse.setResponseLine("200 Ok");

        assertThat(expectedResponse, samePropertyValuesAs(httpRouter.route(path)));
    }

    @Test
    void ItDirectsANonRouteableHttpRequest() {
        String path = "/goodbye";
        HttpRouter httpRouter = new HttpRouter(new GetWithABody(), new GetWithNoBody(), new NonRouteable());
        HttpResponse expectedResponse = new HttpResponse();
        expectedResponse.setResponseLine("405 Method Not Allowed");
        expectedResponse.setHeaders(new ArrayList<String>(Arrays.asList("Allow: HEAD, OPTIONS")));

        assertThat(expectedResponse, samePropertyValuesAs(httpRouter.route(path)));
    }

    private class GetWithNoBody implements Action {
        public HttpResponse act() {
            HttpResponse response = new HttpResponse();
            response.setResponseLine("200 Ok");
            return response;
        }
    }

    private class GetWithABody implements Action {
        public HttpResponse act() {
            HttpResponse response = new HttpResponse();
            response.setResponseLine("200 Ok");
            response.setBody("Hello world");
            return response;
        }
    }

    private class NonRouteable implements Action {
        public HttpResponse act() {
            HttpResponse response = new HttpResponse();
            response.setResponseLine("405 Method Not Allowed");
            response.setHeaders(new ArrayList<String>(Arrays.asList("Allow: HEAD, OPTIONS")));
            return response;
        }
    }
}