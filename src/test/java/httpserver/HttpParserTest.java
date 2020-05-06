package httpserver;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HttpParserTest {
    @Test
    void itParsesAValidHttpRequest() {
        HttpParser httpParser = new HttpParser();
        String body = "Hello world";

        String request = (
                "GET /hello_world HTTP/1.1" +
                        System.lineSeparator() +
                        "Host: localhost:8000" +
                        System.lineSeparator() +
                        "Connection: keep-alive" +
                        System.lineSeparator() +
                        System.lineSeparator() +
                        body
        );

        HttpRequest expectedRequest = new HttpRequest("GET", "/hello_world", body);

        assertThat(expectedRequest, samePropertyValuesAs(httpParser.parse(request).get()));
    }

    @Test
    void itDoesNotParseAnInvalidHttpRequest() {
        HttpParser httpParser = new HttpParser();

        String request = (
                "GET/hello_world HTTP/1.1" +
                        System.lineSeparator()
        );

        assertFalse(httpParser.parse(request).isPresent());
    }
}