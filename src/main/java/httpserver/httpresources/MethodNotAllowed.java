package httpserver.httpresources;

import httpserver.HttpResponse;
import httpserver.Resource;

public class MethodNotAllowed implements Resource {
    @Override
    public HttpResponse act() {
        HttpResponse response = new HttpResponse();
        response.responseLine += "405 Method Not Allowed";
        response.headers.add("Allow: HEAD, OPTIONS");
        return response;
    }
}
