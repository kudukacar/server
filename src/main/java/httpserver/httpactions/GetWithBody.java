package httpserver.httpactions;

import httpserver.HttpResponse;
import httpserver.Action;

public class GetWithBody implements Action {

    @Override
    public HttpResponse act() {
        HttpResponse response = new HttpResponse();
        response.setResponseLine("200 Ok");
        response.setBody("Hello world");
        return response;
    }
}
