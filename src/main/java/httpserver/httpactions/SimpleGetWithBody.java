package httpserver.httpactions;

import httpserver.HttpResponse;
import httpserver.Action;
import httpserver.HttpStatus;

public class SimpleGetWithBody implements Action {

    @Override
    public HttpResponse act(String body) {
        return new HttpResponse.Builder()
                .status(HttpStatus.OK)
                .body("Hello world")
                .build();
    }
}
