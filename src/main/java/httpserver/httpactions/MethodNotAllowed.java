package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;
import httpserver.HttpStatus;

public class MethodNotAllowed implements Action {
    @Override
    public HttpResponse act() {
        return new HttpResponse.Builder()
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .addHeader("Allow: HEAD, OPTIONS")
                .build();
    }
}
