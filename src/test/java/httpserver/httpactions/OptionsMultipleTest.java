package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;
import httpserver.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class OptionsMultipleTest {
    @Test
    void itReturnAnHttpResponseWithStatusCode200AndAllowHeaders() {
        Action action = new OptionsMultiple();
        HttpResponse response = new HttpResponse.Builder()
                .status(HttpStatus.OK)
                .addHeader("Allow: GET, HEAD, OPTIONS, PUT, POST")
                .build();

        assertThat(response, samePropertyValuesAs(action.act()));
    }
}