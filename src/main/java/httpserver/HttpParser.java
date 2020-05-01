package httpserver;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class HttpParser implements Parseable{
    @Override
    public Optional<HttpRequest> parse(String request) {
        List<String> splitRequest = Arrays.asList(request.split(System.lineSeparator()));
        List<String> splitRequestLine = Arrays.asList(splitRequest.get(0).split(" "));
        HttpRequest httpRequest = null;
        if(splitRequestLine.size() == 3)
        {
            httpRequest = new HttpRequest(splitRequestLine.get(0), splitRequestLine.get(1));
        }
        return Optional.ofNullable(httpRequest);
    }
}
