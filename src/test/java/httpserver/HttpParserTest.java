package httpserver;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        Map<String, String> expectedRequest = new HashMap<>();
        expectedRequest.put("path", "/hello_world");
        expectedRequest.put("method", "GET");

        assertEquals(expectedRequest, httpParser.parse(request));
    }
}