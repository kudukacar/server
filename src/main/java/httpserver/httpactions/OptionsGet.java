package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;
import httpserver.HttpStatus;

public class OptionsGet implements Action {
    @Override
    public HttpResponse act(String body) {
        return new HttpResponse.Builder()
                .status(HttpStatus.OK)
                .addHeader("Allow: GET, HEAD, OPTIONS")
                .build();
    }
}
