package httpserver;

import httpserver.httpresources.GetWithBody;
import httpserver.httpresources.GetWithoutBody;
import httpserver.httpresources.MethodNotAllowed;
import infrastructure.Listener;
import infrastructure.Logger;
import infrastructure.Server;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        Executor executor = Executors.newCachedThreadPool();
        Logger logger = new Logger(System.out);
        HttpPresenter presenter = new HttpPresenter();
        HttpParser parser = new HttpParser();
        List<Route> routes = new ArrayList<Route>(Arrays.asList(
                new Route("/simple_get", new GetWithoutBody()),
                new Route("/simple_get_with_body", new GetWithBody())
        ));
        HttpController controller = new HttpController(routes, new MethodNotAllowed());
        HttpResponder responder = new HttpResponder(parser, controller, presenter);

        try(Listener listener = new Listener(serverSocket);) {
            new Server(listener, executor, responder, logger).start();
        }
    }
}
