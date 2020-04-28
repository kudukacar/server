package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;

import java.util.ArrayList;
import java.util.Arrays;

public class MethodNotAllowed implements Action {
    @Override
    public HttpResponse act() {
        HttpResponse response = new HttpResponse();
        response.setResponseLine("405 Method Not Allowed");
        response.setHeaders(new ArrayList<String>(Arrays.asList("Allow: HEAD, OPTIONS")));
        return response;
    }
}
