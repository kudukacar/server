package httpserver.httpactions;

import httpserver.HttpResponse;
import httpserver.Action;

public class SimpleGetWithBody implements Action {

    @Override
    public HttpResponse act() {
        return new HttpResponse.HttpResponseBuilder("200 Ok")
                .body("Hello world")
                .build();
    }
}
