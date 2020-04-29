package httpserver.httpactions;

import httpserver.HttpResponse;
import httpserver.Action;

public class SimpleGetWithoutBody implements Action {
    @Override
    public HttpResponse act() {
        return new HttpResponse.Builder("200 Ok")
                .build();
    }
}
