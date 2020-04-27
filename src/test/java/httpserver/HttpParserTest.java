package httpserver;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpParserTest {
    @Test
    void itParsesTheRequestLineOfAnHttpRequest() {
        HttpParser httpParser = new HttpParser();
        String requestLine = "GET /hello_world HTTP/1.1" + System.lineSeparator();
        ArrayList<String> headers = new ArrayList<String>(Arrays.asList("Host: localhost:8000", "Connection: keep-alive"));
        String allHeaders = String.join(System.lineSeparator(), headers) + System.lineSeparator();
        String blankLine = System.lineSeparator();
        String body = "Hello world";
        String request = requestLine + allHeaders + blankLine + body;
        String path = "/hello_world";

        assertEquals(path, httpParser.parse(request));
    }
}