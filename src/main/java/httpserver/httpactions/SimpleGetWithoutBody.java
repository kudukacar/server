package httpserver.httpactions;

import httpserver.HttpResponse;
import httpserver.Action;

public class SimpleGetWithoutBody implements Action {
    @Override
    public HttpResponse act() {
        return new HttpResponse.HttpResponseBuilder("200 Ok")
                .build();
    }
}
