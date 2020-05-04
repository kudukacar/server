package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;
import httpserver.HttpStatus;

public class BadRequest implements Action {
    @Override
    public HttpResponse act() {
        return new HttpResponse.Builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }
}
