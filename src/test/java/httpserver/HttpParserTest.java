package httpserver;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class HttpParserTest {
    @Test
    void itParsesTheHttpRequest() {
        HttpParser httpParser = new HttpParser();

        String request = (
                "GET /hello_world HTTP/1.1" +
                        System.lineSeparator() +
                        "Host: localhost:8000" +
                        System.lineSeparator() +
                        "Connection: keep-alive" +
                        System.lineSeparator() +
                        System.lineSeparator() +
                        "Hello world"
        );

        HttpRequest expectedRequest = new HttpRequest("GET", "/hello_world");

        assertThat(expectedRequest, samePropertyValuesAs(httpParser.parse(request)));
    }
}