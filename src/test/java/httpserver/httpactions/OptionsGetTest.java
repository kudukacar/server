package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;
import httpserver.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

class OptionsGetTest {
    @Test
    void itReturnsAnHttpResponseWithStatusCode200AndAllowHeaders() {
        Action action = new OptionsGet();
        HttpResponse response = new HttpResponse.Builder()
                .status(HttpStatus.OK)
                .addHeader("Allow: GET, HEAD, OPTIONS")
                .build();

        assertThat(response, samePropertyValuesAs(action.act("")));
    }
}