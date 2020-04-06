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

class ConnectionTest {
    @Test
    public void establishesConnection() throws IOException {
        TestServerSocket serverSocket = new TestServerSocket();

        Socket socket = new Connection().open(serverSocket);

        assertFalse(socket.isClosed());
    }

    @Test
    public void closesConnection() throws IOException {
        TestServerSocket serverSocket = new TestServerSocket();

        Connection connection = new Connection();
        Socket socket = connection.open(serverSocket);
        connection.close(socket);

        assertTrue(socket.isClosed());
    }
}