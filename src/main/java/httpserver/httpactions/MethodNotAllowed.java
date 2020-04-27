package httpserver.httpactions;

import httpserver.HttpResponse;
import httpserver.Action;

public class MethodNotAllowed implements Action {
    @Override
    public HttpResponse act() {
        HttpResponse response = new HttpResponse();
        response.setResponseLine("405 Method Not Allowed");
        response.setHeaders("Allow: HEAD, OPTIONS");
        return response;
    }
}
