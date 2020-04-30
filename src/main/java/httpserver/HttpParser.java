package httpserver;

import java.util.Arrays;
import java.util.List;

public class HttpParser implements Parseable{
    @Override
    public HttpRequest parse(String request) {
        List<String> splitRequest = Arrays.asList(request.split(System.lineSeparator()));
        List<String> splitRequestLine = Arrays.asList(splitRequest.get(0).split(" "));
        return new HttpRequest(splitRequestLine.get(0), splitRequestLine.get(1));
    }
}
