package httpserver.httpactions;

import httpserver.HttpResponse;
import httpserver.Action;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class GetWithBodyTest {
    @Test
    void ItReturnsAnHttpResponseWithStatus200AndBodyHelloworld() {
        Action action = new GetWithBody();
        HttpResponse response = new HttpResponse();
        response.setResponseLine("200 Ok");
        response.setBody("Hello world");

        assertThat(response, samePropertyValuesAs(action.act()));
    }
}