package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;
import httpserver.HttpStatus;

public class Redirect implements Action {
    @Override
    public HttpResponse act(String body) {
        return new HttpResponse.Builder()
                .status(HttpStatus.REDIRECT)
                .addHeader("Location: http://127.0.0.1:5000/simple_get")
                .build();
    }
}
