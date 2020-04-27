package httpserver.httpactions;

import httpserver.HttpResponse;
import httpserver.Action;

public class GetWithoutBody implements Action {
    @Override
    public HttpResponse act() {
        HttpResponse response = new HttpResponse();
        response.setResponseLine("200 Ok");
        return response;
    }
}
