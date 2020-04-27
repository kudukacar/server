package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class MethodNotAllowedTest {
    @Test
    void ItReturnsAnHttpResponseWithStatus405AndAllowHeaders() {
        Action action = new MethodNotAllowed();
        HttpResponse response = new HttpResponse();
        response.setResponseLine("405 Method Not Allowed");
        response.setHeaders(new ArrayList<String>(Arrays.asList("Allow: HEAD, OPTIONS")));

        assertThat(response, samePropertyValuesAs(action.act()));
    }
}