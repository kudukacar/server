package echoserver;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.*;

import static org.junit.jupiter.api.Assertions.*;

class TestServerSocket extends ServerSocket {

    public TestServerSocket() throws IOException {
    }

    public Socket accept() {
        return new Socket();
    }
}

class ListenerTest {
    @Test
    public void itEstablishesConnection() throws IOException {
        TestServerSocket serverSocket = new TestServerSocket();

        SocketConnection socketConnection = new Listener(serverSocket).open();

        assertFalse(socketConnection.socket.isClosed());
    }

    @Test
    public void itClosesConnection() throws IOException {
        TestServerSocket serverSocket = new TestServerSocket();
        Listener listener = new Listener(serverSocket);

        listener.close();

        assertTrue(serverSocket.isClosed());
    }
}