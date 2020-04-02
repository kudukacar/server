package echoserver;

import java.io.IOException;
import java.net.*;

public class Connection {
    public Socket open(ServerSocket serverSocket) throws IOException {
        return serverSocket.accept();
    }

    public void close(Socket socket) throws IOException {
        socket.close();
    }
}
