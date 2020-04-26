package httpserver;

import infrastructure.Connection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpResponderTest {

    @Test
    void itRespondsToAnHttpRequest() throws IOException {
        String expectedResponse = "HTTP/1.1 200 Ok" + System.lineSeparator();
        Presentable presenter = new SimpleHttpResponsePresenter(expectedResponse);
        HttpResponder responder = new HttpResponder(presenter);
        TestConnection connection = new TestConnection();

        responder.respond(connection);

        assertEquals(connection.written, expectedResponse);
    }

    private class TestConnection implements Connection {
        public String written;

        @Override
        public String read() throws IOException {
            return null;
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

        public SimpleHttpResponsePresenter(String response) {
            this.response = response;
        }

        @Override
        public String present() {
            return response;
        }
    }
}