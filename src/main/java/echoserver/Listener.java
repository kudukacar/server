package echoserver;

import java.io.IOException;
import java.net.*;

public class Listener {
    private final ServerSocket serverSocket;

    public Listener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public ClientServerConnection open() throws IOException {
        Socket socket = this.serverSocket.accept();
        return new ClientServerConnection(socket);
    }

    public void close() throws IOException {
        this.serverSocket.close();
    }
}
