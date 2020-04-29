package httpserver;

import java.util.Map;

public interface Parseable {
    Map<String, String> parse(String request);
}
