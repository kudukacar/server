package httpserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpResponseTest {
    @Test
    void itCreatesAnHttpResponse() {
        String statusCode = "405";
        String statusName =  "Method Not Allowed";
        String responseLine = "HTTP/1.1 " + statusCode + " " + statusName;
        String header = "Allow: HEAD, OPTIONS";
        String body = "Method is not allowed";

        HttpResponse response =  new HttpResponse.Builder()
                .statusCode(statusCode)
                .statusName(statusName)
                .addHeader(header)
                .body(body)
                .build();

        assertEquals(response.getResponseLine(), responseLine);
        assertEquals(response.getBody(), body);
        assertEquals(response.getHeaders().get(0), header);
    }
}