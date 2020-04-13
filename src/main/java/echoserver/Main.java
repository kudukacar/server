package echoserver;

import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        String welcomeMessage = "Welcome.  Enter a text to echo.  Press control + C to end.";
        Echoer echoer = new Echoer(welcomeMessage);

        try(Listener listener = new Listener(serverSocket);) {
            new EchoServer(listener, echoer).start();
        }
    }
}