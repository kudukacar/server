package Infrastructure;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener implements Listenable {
    private final ServerSocket serverSocket;

    public Listener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Connection listen() throws IOException {
        Socket socket = this.serverSocket.accept();
        return new SocketConnection(socket);
    }

    public void close() throws Exception {
        this.serverSocket.close();
    }
}
