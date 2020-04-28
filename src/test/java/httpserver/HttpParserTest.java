package httpserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpParserTest {
    @Test
    void itParsesTheRequestLineOfAnHttpRequest() {
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

        String path = "/hello_world";

        assertEquals(path, httpParser.parse(request));
    }
}