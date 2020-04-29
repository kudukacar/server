package httpserver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpParser implements Parseable{
    @Override
    public Map<String, String> parse(String request) {
        List<String> splitRequest = Arrays.asList(request.split(System.lineSeparator()));
        List<String> splitRequestLine = Arrays.asList(splitRequest.get(0).split(" "));
        Map<String, String> parsedRequest = new HashMap<>();
        parsedRequest.put("method", splitRequestLine.get(0));
        parsedRequest.put("path", splitRequestLine.get(1));
        return parsedRequest;
    }
}
