package echoserver;

import infrastructure.Listener;

import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        Executor executor = Executors.newCachedThreadPool();
        Logger logger = new Logger(System.out);
        String welcomeMessage = "Welcome.  Enter a text to echo.  Press control + C to end.";
        Echoer echoer = new Echoer(welcomeMessage);

        try(Listener listener = new Listener(serverSocket);) {
            new EchoServer(listener, executor, echoer, logger).start();
        }
    }
}
