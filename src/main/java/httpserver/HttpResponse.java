package httpserver;

import java.util.List;

public class HttpResponse {
    private final String responseLine;
    private final String body;
    private final List<String> headers;

    private HttpResponse(HttpResponseBuilder builder) {
        this.responseLine = "HTTP/1.1 " + builder.responseLine;
        this.body = builder.body;
        this.headers = builder.headers;
    }

    public String getResponseLine() {
        return this.responseLine;
    }

    public String getBody() {
        return this.body;
    }

    public List<String> getHeaders() {
        return this.headers;
    }

    public static class HttpResponseBuilder {
        private final String responseLine;
        private String body;
        private List<String> headers;

        public HttpResponseBuilder(String responseLine) {
            this.responseLine = responseLine;
        }
        public HttpResponseBuilder body(String body) {
            this.body = body;
            return this;
        }
        public HttpResponseBuilder headers(List<String> headers) {
            this.headers = headers;
            return this;
        }
        public HttpResponse build() {
            return new HttpResponse(this);
        }
    }
}
