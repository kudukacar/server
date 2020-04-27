package httpserver;

import java.util.Arrays;
import java.util.List;

public class HttpParser implements Parseable{
    @Override
    public String parse(String request) {
        List<String> splitRequest = Arrays.asList(request.split(System.lineSeparator()));
        List<String> splitResponseLine = Arrays.asList(splitRequest.get(0).split(" "));
        return splitResponseLine.get(1);
    }



}
