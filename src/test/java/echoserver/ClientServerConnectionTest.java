package echoserver;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ClientServerConnectionTest {
    @Test
    void itReadsFromInputStream() throws IOException {
        String input = "Connected";
        Socket socket = new FakeSocket(input);
        ClientServerConnection clientServerConnection = new ClientServerConnection(socket);

        assertEquals(input, clientServerConnection.read());
    }

    @Test
    void itWritesToOutputStream() throws IOException {
        String input = "Connected";
        Socket socket = new FakeSocket(input);
        ClientServerConnection clientServerConnection = new ClientServerConnection(socket);

        clientServerConnection.write(input);

        assertEquals(input + "\n", socket.getOutputStream().toString());
    }

    @Test
    void itClosesConnection() throws IOException {
        Socket socket = new FakeSocket("Connected");
        ClientServerConnection clientServerConnection = new ClientServerConnection(socket);

        clientServerConnection.close();

        assertTrue(clientServerConnection.socket.isClosed());
    }

    private class FakeSocket extends Socket {

        private final ByteArrayOutputStream output;
        private final ByteArrayInputStream input;

        public FakeSocket(String input) {
            this.output = new ByteArrayOutputStream();
            this.input = new ByteArrayInputStream((input.getBytes()));
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            return this.output;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return this.input;
        }
    }
}