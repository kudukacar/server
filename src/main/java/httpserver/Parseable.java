package httpserver;

import java.util.Optional;

public interface Parseable {
    Optional<HttpRequest> parse(String request);
}
