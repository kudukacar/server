package echoserver;

import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        Echoer echoer = new Echoer();

        try(Listener listener = new Listener(serverSocket);) {
            new EchoServer(listener, echoer).start();
            System.out.println("hello");
        }
    }
}