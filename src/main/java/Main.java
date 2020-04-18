import Infrastructure.Connection;
import Infrastructure.Listener;

import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        Listener listener = new Listener(serverSocket);
        Connection connection = listener.listen();
        connection.close();
    }
}