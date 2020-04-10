package echoserver;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        EchoClient echoClient = new EchoClient();

        try(Listener listener = new Listener(serverSocket);) {
            new EchoServer(listener, echoClient).start();
        }
    }
}