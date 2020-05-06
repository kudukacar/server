package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;
import httpserver.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class SimplePostTest {
    @Test
    void itReturnsAnHttpResponseOfStatusOkAndSameBodyAsRequest() {
        Action action = new SimplePost();
        String body = "body";
        HttpResponse httpResponse = new HttpResponse.Builder()
                .status(HttpStatus.OK)
                .body(body)
                .build();

        assertThat(httpResponse, samePropertyValuesAs(action.act(body)));
    }
}