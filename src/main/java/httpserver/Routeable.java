package httpserver;

import java.util.Map;

public interface Routeable {
    HttpResponse route(Map<String, String> request);
}
