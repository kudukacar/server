package httpserver.httpactions;

import httpserver.HttpResponse;
import httpserver.Action;
import httpserver.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class SimpleGetWithBodyTest {
    @Test
    void ItReturnsAnHttpResponseWithStatus200AndBodyHelloworld() {
        Action action = new SimpleGetWithBody();
        HttpResponse response = new HttpResponse.Builder()
                .status(HttpStatus.OK)
                .body("Hello world")
                .build();

        assertThat(response, samePropertyValuesAs(action.act("")));
    }
}