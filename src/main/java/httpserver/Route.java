package httpserver;

public class Route {
    public final String path;
    public final Resource httpResource;

    public Route(String path, Resource httpResource) {
        this.path = path;
        this.httpResource = httpResource;
    }
}
