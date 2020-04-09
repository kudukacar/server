package echoserver;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        Listener listener = new Listener(serverSocket);
        EchoClient echoClient = new EchoClient();
        new EchoServer(listener, echoClient).connect();
    }
}