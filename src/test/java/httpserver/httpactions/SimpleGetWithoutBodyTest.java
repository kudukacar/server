package httpserver.httpactions;

import httpserver.HttpResponse;
import httpserver.Action;
import httpserver.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class SimpleGetWithoutBodyTest {
    @Test
    void ItReturnsAnHttpResponseWithStatus200AndEmptyBody() {
        Action action = new SimpleGetWithoutBody();
        HttpResponse response = new HttpResponse.Builder()
                .status(HttpStatus.OK)
                .build();

        assertThat(response, samePropertyValuesAs(action.act("")));
    }
}