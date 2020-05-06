package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;
import httpserver.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

class NotFoundTest {
    @Test
    void itReturnsAnHttpResponseWithStatus404NotFound() {
        Action action = new NotFound();
        HttpResponse httpResponse = new HttpResponse.Builder()
                .status(HttpStatus.NOT_FOUND)
                .build();

        assertThat(httpResponse, samePropertyValuesAs(action.act("")));
    }
}