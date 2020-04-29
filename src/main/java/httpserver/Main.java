package httpserver;

import httpserver.httpactions.MethodNotAllowed;
import httpserver.httpactions.SimpleGetWithBody;
import httpserver.httpactions.SimpleGetWithoutBody;
import infrastructure.Listener;
import infrastructure.Logger;
import infrastructure.Server;

import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        Executor executor = Executors.newCachedThreadPool();
        Logger logger = new Logger(System.out);
        HttpPresenter presenter = new HttpPresenter();
        HttpParser parser = new HttpParser();

        String GET = "GET";
        String HEAD = "HEAD";
        Map<String, Map<String, Action>> routes = new HashMap<>();

        routes.put("/simple_get", Stream.of(new Object[][] {
                {GET, new SimpleGetWithoutBody()},
                {HEAD, new SimpleGetWithoutBody()},
        }).collect(Collectors.toMap(entries -> (String) entries[0], entries -> (Action) entries[1])));
        routes.put("/simple_get_with_body", Stream.of(new Object[][] {
                {GET, new SimpleGetWithBody()},
        }).collect(Collectors.toMap(entries -> (String) entries[0], entries -> (Action) entries[1])));
        routes.put("/head_request", Stream.of(new Object[][] {
                {HEAD, new SimpleGetWithoutBody()},
        }).collect(Collectors.toMap(entries -> (String) entries[0], entries -> (Action) entries[1])));

        HttpRouter router = new HttpRouter(routes, new MethodNotAllowed());
        HttpResponder responder = new HttpResponder(parser, router, presenter);

        try(Listener listener = new Listener(serverSocket);) {
            new Server(listener, executor, responder, logger).start();
        }
    }
}
