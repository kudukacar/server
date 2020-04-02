package echoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        Connection connection = new Connection();
        Socket socket = connection.open(serverSocket);
        connection.close(socket);
    }
}