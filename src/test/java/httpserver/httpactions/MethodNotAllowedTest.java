package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class MethodNotAllowedTest {
    @Test
    void ItReturnsAnHttpResponseWithStatus405AndAllowHeaders() {
        Action action = new MethodNotAllowed();
        HttpResponse response = new HttpResponse.Builder()
                .statusCode("405")
                .statusName("Method Not Allowed")
                .addHeader("Allow: HEAD, OPTIONS")
                .build();

        assertThat(response, samePropertyValuesAs(action.act()));
    }
}