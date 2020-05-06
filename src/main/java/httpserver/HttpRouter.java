package httpserver;

import java.util.Optional;

public class HttpRouter implements Routeable {

    private final Action methodNotAllowed;
    private final HttpRoutes routes;
    private final Action badRequest;
    private final Action notFound;

    public HttpRouter(HttpRoutes routes, Action methodNotAllowed, Action badRequest, Action notFound) {
        this.routes = routes;
        this.methodNotAllowed = methodNotAllowed;
        this.badRequest = badRequest;
        this.notFound = notFound;
    }

    public HttpResponse route(Optional<HttpRequest> request) {
        return request.map(this::routeValidRequest).orElse(badRequest.act());
    }

    private HttpResponse routeValidRequest(HttpRequest request) {
        if(routes.getPath(request.getPath())) {
            return routes.getAction(request.getPath(), request.getMethod())
                    .orElse(methodNotAllowed).act();
        } else {
            return notFound.act();
        }
    }
}
