package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;
import httpserver.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class MethodNotAllowedTest {
    @Test
    void ItReturnsAnHttpResponseWithStatus405AndAllowHeaders() {
        Action action = new MethodNotAllowed();
        HttpResponse response = new HttpResponse.Builder()
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .addHeader("Allow: HEAD, OPTIONS")
                .build();

        assertThat(response, samePropertyValuesAs(action.act("")));
    }
}