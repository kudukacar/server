package echoserver;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class SocketConnectionTest {
    @Test
    void itReadsFromInputStream() throws IOException {
        String input = "Connected";
        Socket socket = new FakeSocket(input);
        SocketConnection socketConnection = new SocketConnection(socket);

        assertEquals(input, socketConnection.read());
    }

    @Test
    void itWritesToOutputStream() throws IOException {
        String input = "Connected";
        Socket socket = new FakeSocket(input);
        SocketConnection socketConnection = new SocketConnection(socket);

        socketConnection.write(input);

        assertEquals(input + "\n", socket.getOutputStream().toString());
    }

    @Test
    void itClosesConnection() throws IOException {
        Socket socket = new FakeSocket("Connected");
        SocketConnection socketConnection = new SocketConnection(socket);

        socketConnection.close();

        assertTrue(socketConnection.isClosed());
    }

    @Test
    void itReturnsFalseWhenConnectionIsOpen() {
        Socket socket = new FakeSocket("Connected");
        SocketConnection socketConnection = new SocketConnection(socket);

        assertFalse(socketConnection.isClosed());
    }

    private class FakeSocket extends Socket {

        private final ByteArrayOutputStream output;
        private final ByteArrayInputStream input;

        public FakeSocket(String input) {
            this.output = new ByteArrayOutputStream();
            this.input = new ByteArrayInputStream((input.getBytes()));
        }

        @Override
        public OutputStream getOutputStream() {
            return this.output;
        }

        @Override
        public InputStream getInputStream() {
            return this.input;
        }
    }
}