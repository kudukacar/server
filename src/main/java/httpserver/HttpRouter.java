package httpserver;

import java.util.Map;

public class HttpRouter implements Routeable {

    private final Action methodNotAllowed;
    private final Map<String, Map<String, Action>> routes;

    public HttpRouter(Map<String, Map<String, Action>> routes, Action methodNotAllowed) {
        this.routes = routes;
        this.methodNotAllowed = methodNotAllowed;
    }

    public HttpResponse route(Map<String, String> request) {
        String path = request.get("path");
        String method = request.get("method");
        if(routes.containsKey(path)) {
            return routes.get(path).getOrDefault(method, methodNotAllowed).act();
        } else {
            return methodNotAllowed.act();
        }
    }
}
