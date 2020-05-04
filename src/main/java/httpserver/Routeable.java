package httpserver;

import java.util.Optional;

public interface Routeable {
    HttpResponse route(Optional<HttpRequest> request);
}
