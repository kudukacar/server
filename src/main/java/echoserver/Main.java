package echoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        Listener listener = new Listener(serverSocket);
        ClientServerConnection clientServerConnection = listener.open();
        new EchoServer().echo(clientServerConnection);
        listener.close();
    }
}