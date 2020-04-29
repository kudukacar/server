package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;

import java.util.ArrayList;
import java.util.Arrays;

public class MethodNotAllowed implements Action {
    @Override
    public HttpResponse act() {
        return new HttpResponse.HttpResponseBuilder("405 Method Not Allowed")
                .headers(new ArrayList<String>(Arrays.asList("Allow: HEAD, OPTIONS")))
                .build();
    }
}
