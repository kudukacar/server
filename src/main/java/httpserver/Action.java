package httpserver;

public interface Action {
    HttpResponse act(String body);
}
