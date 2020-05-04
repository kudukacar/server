package httpserver;

import infrastructure.Connection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpResponderTest {

    @Test
    void itRespondsToAnHttpRequest() throws IOException {
        String request = "GET /hello_world HTTP/1.1" + System.lineSeparator();
        String expectedResponse = "HTTP/1.1 200 Ok" + System.lineSeparator();
        Parseable parser = new SimpleHttpParser();
        Routeable router = new SimpleHttpRouter();
        Presentable presenter = new SimpleHttpResponsePresenter();
        HttpResponder responder = new HttpResponder(parser, router, presenter);
        TestConnection connection = new TestConnection(request);

        responder.respond(connection);

        assertEquals(expectedResponse, connection.written);
    }

    private static class TestConnection implements Connection {
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

    private static class SimpleHttpResponsePresenter implements Presentable {
        @Override
        public String present(HttpResponse response) {
            return response.getVersion() + " " + response.getStatus() + System.lineSeparator();
        }
    }

    private static class SimpleHttpParser implements Parseable {
        @Override
        public Optional<HttpRequest> parse(String request) {
            return Optional.of(new HttpRequest(request, request));
        }
    }

    private static class SimpleHttpRouter implements Routeable {
        public HttpResponse route(Optional<HttpRequest> request) {
            HttpRequest httpRequest = request.get();
            if(httpRequest.getMethod().equals(httpRequest.getPath())) {
                return new HttpResponse.Builder()
                        .status(HttpStatus.OK)
                        .build();
            } else {
                return null;
            }
        }
    }
}