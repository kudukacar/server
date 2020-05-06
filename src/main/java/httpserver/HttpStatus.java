package httpserver;

public final class HttpStatus {
    public static final String OK = "200 Ok";
    public static final String METHOD_NOT_ALLOWED = "405 Method Not Allowed";
    public static final String BAD_REQUEST = "400 Bad Request";
    public static final String NOT_FOUND = "404 Not Found";

    private HttpStatus() {}
}
