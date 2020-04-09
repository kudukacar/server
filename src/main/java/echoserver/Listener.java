package echoserver;

import java.io.IOException;
import java.net.*;

public class Listener implements Listenable{
    private final ServerSocket serverSocket;

    public Listener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Connection open() throws IOException {
        Socket socket = this.serverSocket.accept();
        return new SocketConnection(socket);
    }

    public void close() throws IOException {
        this.serverSocket.close();
    }
}
