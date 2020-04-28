package infrastructure;

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

        assertEquals(input, socket.getOutputStream().toString());
    }

    @Test
    void itClosesTheOutputAndInputChannels() throws IOException {
        Socket socket = new FakeSocket("Connected");
        SocketConnection socketConnection = new SocketConnection(socket);

        socketConnection.close();

        assertTrue(socketConnection.isClosed());
    }

    @Test
    void itReturnsFalseWhenOutputOrInputConnectionsAreOpen() {
        Socket socket = new FakeSocket("Connected");
        SocketConnection socketConnection = new SocketConnection(socket);

        assertFalse(socketConnection.isClosed());
    }

    private class FakeSocket extends Socket {

        private final ByteArrayOutputStream output;
        private final ByteArrayInputStream input;
        public boolean outputClosed;
        public boolean inputClosed;

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

        @Override
        public void shutdownOutput() {
            outputClosed = true;
        }

        @Override
        public void shutdownInput() {
            inputClosed = true;
        }

        public boolean isOutputShutdown() {
            return outputClosed;
        }

        public boolean isInputShutdown() {
            return inputClosed;
        }
    }
}