package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;
import httpserver.HttpStatus;

public class BadRequest implements Action {
    public HttpResponse act(String body) {
        return new HttpResponse.Builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }
}
