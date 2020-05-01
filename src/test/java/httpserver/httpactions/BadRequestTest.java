package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;
import httpserver.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class BadRequestTest {
    @Test
    void itReturnsAnHttpResponseWithStatusCode400() {
        Action action = new BadRequest();
        HttpResponse response = new HttpResponse.Builder()
                .status(HttpStatus.BADREQUEST)
                .build();

        assertThat(response, samePropertyValuesAs(action.act()));
    }
}