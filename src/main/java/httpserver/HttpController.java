package httpserver;

import java.util.List;
import java.util.stream.Collectors;

public class HttpController implements Controller{
    private final List<Route> routes;
    private final Resource nonRouteable;

    public HttpController(List<Route> routes, Resource nonRouteable) {
        this.routes = routes;
        this.nonRouteable = nonRouteable;
    }

    public HttpResponse control(String request) {
        return selectResource(request).act();
    }

    private Resource selectResource(String request) {
        List<Route> selectedRoute =  routes.stream().filter(
                route -> route.path.equals(request)).collect(Collectors.toList()
        );
        if(selectedRoute.isEmpty()) {
            return nonRouteable;
        } else {
            return selectedRoute.get(0).httpResource;
        }
    }
}
