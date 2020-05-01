package httpserver;

import java.util.Optional;

public class HttpRouter implements Routeable {

    private final Action methodNotAllowed;
    private final HttpRoutes routes;
    private final Action badRequest;

    public HttpRouter(HttpRoutes routes, Action methodNotAllowed, Action badRequest) {
        this.routes = routes;
        this.methodNotAllowed = methodNotAllowed;
        this.badRequest = badRequest;
    }

    public HttpResponse route(Optional<HttpRequest> request) {
        return request.map(this::routeValidRequest).orElse(badRequest.act());
    }

    private HttpResponse routeValidRequest(HttpRequest request) {
        return routes.getAction(request.getPath(), request.getMethod())
                .orElse(methodNotAllowed).act();
    }
}
