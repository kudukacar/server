package httpserver.httpactions;

import httpserver.HttpResponse;
import httpserver.Action;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class SimpleGetWithoutBodyTest {
    @Test
    void ItReturnsAnHttpResponseWithStatus200AndEmptyBody() {
        Action action = new SimpleGetWithoutBody();
        HttpResponse response = new HttpResponse.Builder()
                .statusCode("200")
                .statusName("Ok")
                .build();

        assertThat(response, samePropertyValuesAs(action.act()));
    }
}