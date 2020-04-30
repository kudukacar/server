package httpserver;

import httpserver.httpactions.MethodNotAllowed;
import httpserver.httpactions.SimpleGetWithBody;
import httpserver.httpactions.SimpleGetWithoutBody;
import infrastructure.Listener;
import infrastructure.Logger;
import infrastructure.Server;

import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        Executor executor = Executors.newCachedThreadPool();
        Logger logger = new Logger(System.out);
        HttpPresenter presenter = new HttpPresenter();
        HttpParser parser = new HttpParser();

        String GET = "GET";
        String HEAD = "HEAD";
        String simple_get = "/simple_get";

        Map<String, Map<String, Action>> routes = new HttpRoutes.Builder()
                .addRoute(simple_get, GET, new SimpleGetWithoutBody())
                .addRoute(simple_get, HEAD, new SimpleGetWithoutBody())
                .addRoute("/simple_get_with_body", GET, new SimpleGetWithBody())
                .addRoute("/head_request", HEAD, new SimpleGetWithoutBody())
                .build()
                .getRoutes();

        HttpRouter router = new HttpRouter(routes, new MethodNotAllowed());
        HttpResponder responder = new HttpResponder(parser, router, presenter);

        try(Listener listener = new Listener(serverSocket);) {
            new Server(listener, executor, responder, logger).start();
        }
    }
}
