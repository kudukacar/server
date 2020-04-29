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
        HttpResponse response = new HttpResponse.HttpResponseBuilder("405 Method Not Allowed")
                .headers(new ArrayList<String>(Arrays.asList("Allow: HEAD, OPTIONS")))
                .build();

        assertThat(response, samePropertyValuesAs(action.act()));
    }
}