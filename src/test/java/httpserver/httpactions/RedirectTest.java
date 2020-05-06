package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;
import httpserver.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class RedirectTest {
    @Test
    void itReturnsAnHttpResponseWithStatus301AndLocationHeader() {
        Action action = new Redirect();
        HttpResponse httpResponse = new HttpResponse.Builder()
                .status(HttpStatus.REDIRECT)
                .addHeader("Location: http://0.0.0.0:5000/simple_get")
                .build();

        assertThat(httpResponse, samePropertyValuesAs(action.act()));
    }
}