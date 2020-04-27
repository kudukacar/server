package httpserver.httpresources;

import httpserver.HttpResponse;
import httpserver.Resource;

public class GetWithoutBody implements Resource {
    @Override
    public HttpResponse act() {
        HttpResponse response = new HttpResponse();
        response.responseLine += "200 Ok";
        return response;
    }
}
