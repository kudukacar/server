package httpserver.httpresources;

import httpserver.HttpResponse;
import httpserver.Resource;

public class GetWithBody implements Resource {

    @Override
    public HttpResponse act() {
        HttpResponse response = new HttpResponse();
        response.responseLine += "200 Ok";
        response.body = "Hello world";
        return response;
    }
}
