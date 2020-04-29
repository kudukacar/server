package httpserver;

public interface Parseable {
    HttpRequest parse(String request);
}
