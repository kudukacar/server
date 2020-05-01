package httpserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpResponseTest {
    @Test
    void itCreatesAnHttpResponse() {
        String status = HttpStatus.METHODNOTALLOWED;
        String header = "Allow: HEAD, OPTIONS";
        String body = "Method is not allowed";

        HttpResponse response =  new HttpResponse.Builder()
                .status(status)
                .addHeader(header)
                .body(body)
                .build();

        assertEquals(response.getStatus(), status);
        assertEquals(response.getBody(), body);
        assertEquals(response.getHeaders().get(0), header);
    }
}