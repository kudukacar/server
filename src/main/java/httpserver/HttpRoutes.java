package httpserver;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HttpRoutes {
    private final Map<String, Map<String, Action>> routes;

    private HttpRoutes(Builder builder) {
        this.routes = builder.routes;
    }

    public Map<String, Map<String, Action>> getRoutes() {
        return routes;
    }

    public static class Builder {
        private Map<String, Map<String, Action>> routes = new HashMap<>();

        public Builder addRoute(String path, String method, Action action) {
            if(routes.containsKey(path)) {
                routes.get(path).put(method, action);
            }else {
                routes.put(path, Stream.of(new AbstractMap.SimpleEntry<>(method, action))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
            }
            return this;
        }
        public HttpRoutes build() {
            return new HttpRoutes(this);
        }
    }
}
