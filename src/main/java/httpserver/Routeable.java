package httpserver;

public interface Routeable {
    HttpResponse route(HttpRequest request);
}
