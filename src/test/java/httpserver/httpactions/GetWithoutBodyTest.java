package httpserver.httpactions;

import httpserver.HttpResponse;
import httpserver.Action;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class GetWithoutBodyTest {
    @Test
    void ItReturnsAnHttpResponseWithStatus200AndEmptyBody() {
        Action action = new GetWithoutBody();
        HttpResponse response = new HttpResponse();
        response.setResponseLine("200 Ok");

        assertThat(response, samePropertyValuesAs(action.act()));
    }
}