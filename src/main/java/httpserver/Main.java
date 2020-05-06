package httpserver;

import httpserver.httpactions.*;
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

        String GET = "GET";
        String HEAD = "HEAD";
        String OPTIONS = "OPTIONS";
        String POST = "POST";
        String simple_get = "/simple_get";

        HttpRoutes routes = new HttpRoutes.Builder()
                .addRoute(simple_get, GET, new SimpleGetWithoutBody())
                .addRoute(simple_get, HEAD, new SimpleGetWithoutBody())
                .addRoute("/simple_get_with_body", GET, new SimpleGetWithBody())
                .addRoute("/head_request", HEAD, new SimpleGetWithoutBody())
                .addRoute("/redirect", GET, new Redirect())
                .addRoute("/method_options", OPTIONS, new OptionsGet())
                .addRoute("/method_options2", OPTIONS, new OptionsMultiple())
                .addRoute("/echo_body", POST, new SimplePost())
                .build();

        HttpRouter router = new HttpRouter(routes, new MethodNotAllowed(), new BadRequest(), new NotFound());
        HttpResponder responder = new HttpResponder(parser, router, presenter);

        try(Listener listener = new Listener(serverSocket);) {
            new Server(listener, executor, responder, logger).start();
        }
    }
}
