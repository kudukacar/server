package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;
import httpserver.HttpStatus;

public class NotFound implements Action {
    @Override
    public HttpResponse act(String body) {
        return new HttpResponse.Builder()
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
