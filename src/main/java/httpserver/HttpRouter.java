package httpserver;

import java.util.Map;

public class HttpRouter implements Routeable {

    private final Action methodNotAllowed;
    private final Map<String, Map<String, Action>> routes;
    private final Action badRequest;

    public HttpRouter(HttpRoutes routes, Action methodNotAllowed, Action badRequest) {
        this.routes = routes.getRoutes();
        this.methodNotAllowed = methodNotAllowed;
        this.badRequest = badRequest;
    }

    public HttpResponse route(HttpRequest request) {
        if(request == null) {
            return badRequest.act();
        } else {
            return routeValidRequest(request);
        }
    }

    private HttpResponse routeValidRequest(HttpRequest request) {
        String path = request.getPath();
        if(routes.containsKey(path)) {
            return routes.get(path).getOrDefault(request.getMethod(), methodNotAllowed).act();
        } else {
            return methodNotAllowed.act();
        }
    }
}
