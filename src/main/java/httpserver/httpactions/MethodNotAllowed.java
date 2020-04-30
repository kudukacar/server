package httpserver.httpactions;

import httpserver.Action;
import httpserver.HttpResponse;

public class MethodNotAllowed implements Action {
    @Override
    public HttpResponse act() {
        return new HttpResponse.Builder()
                .statusCode("405")
                .statusName("Method Not Allowed")
                .addHeader("Allow: HEAD, OPTIONS")
                .build();
    }
}
