package httpserver;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class HttpParser implements Parseable{
    @Override
    public Optional<HttpRequest> parse(String request) {
        List<String> requestComponents = Arrays.asList(request.split(System.lineSeparator()));
        List<String> requestLineComponents = Arrays.asList(requestComponents.get(0).split(" "));
        if(requestLineComponents.size() == 3)
        {
            String method = requestLineComponents.get(0);
            String path = requestLineComponents.get(1);
            return Optional.of(new HttpRequest(method, path, body(requestComponents)));
        }
        return Optional.empty();
    }

    private String body(List<String> requestComponents) {
        if(requestComponents.size() > 1) {
            return requestComponents.get(requestComponents.size() - 1);
        } else {
            return "";
        }
    }

}
