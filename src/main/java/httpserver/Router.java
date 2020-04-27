package httpserver;

public interface Router {
    HttpResponse route(String httpRequest);
}
