package httpserver;

import httpserver.httpactions.GetWithBody;
import httpserver.httpactions.GetWithoutBody;
import httpserver.httpactions.MethodNotAllowed;
import infrastructure.Listener;
import infrastructure.Logger;
import infrastructure.Server;

import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        Executor executor = Executors.newCachedThreadPool();
        Logger logger = new Logger(System.out);
        HttpPresenter presenter = new HttpPresenter();
        HttpParser parser = new HttpParser();
        HttpRouter router = new HttpRouter(new GetWithBody(), new GetWithoutBody(), new MethodNotAllowed());
        HttpResponder responder = new HttpResponder(parser, router, presenter);

        try(Listener listener = new Listener(serverSocket);) {
            new Server(listener, executor, responder, logger).start();
        }
    }
}
