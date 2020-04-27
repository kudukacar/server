package httpserver;

import infrastructure.Connection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpResponderTest {

    @Test
    void itRespondsToAnHttpRequest() throws IOException {
        String request = "GET /hello_world HTTP/1.1" + System.lineSeparator();
        String expectedResponse = "HTTP/1.1 200 Ok" + System.lineSeparator();
        Parseable parser = new SimpleHttpParser();
        Router router = new SimpleHttpRouter();
        Presentable presenter = new SimpleHttpResponsePresenter();
        HttpResponder responder = new HttpResponder(parser, router, presenter);
        TestConnection connection = new TestConnection(request);

        responder.respond(connection);

        assertEquals(expectedResponse, connection.written);
    }

    private class TestConnection implements Connection {
        private final String request;
        public String written;

        public TestConnection(String request) {
            this.request = request;
        }

        @Override
        public String read() throws IOException {
            return this.request;
        }

        @Override
        public void write(String output) throws IOException {
            written = output;
        }

        @Override
        public void close() throws IOException {

        }

        @Override
        public boolean isClosed() {
            return false;
        }
    }

    private class SimpleHttpResponsePresenter implements Presentable {
        private String response;

        @Override
        public String present(HttpResponse response) {
            return response.getResponseLine() + System.lineSeparator();
        }
    }

    private class SimpleHttpParser implements Parseable {

        @Override
        public String parse(String request) {
            return request;
        }
    }

    private class SimpleHttpRouter implements Router {
        public HttpResponse route(String request) {
            HttpResponse response = new HttpResponse();
            response.setResponseLine("200 Ok");
            return response;
        }
    }
}