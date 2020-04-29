package httpserver;

import java.util.ArrayList;
import java.util.List;

public class HttpResponse {
    private final String responseLine;
    private final String body;
    private final List<String> headers;

    private HttpResponse(Builder builder) {
        this.responseLine = "HTTP/1.1 " + builder.status;
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

    public static class Builder {
        private final String status;
        private String body = "";
        private List<String> headers = new ArrayList<>();

        public Builder(String status) {
            this.status = status;
        }
        public Builder body(String body) {
            this.body = body;
            return this;
        }
        public Builder addHeader(String header) {
            this.headers.add(header);
            return this;
        }
        public HttpResponse build() {
            return new HttpResponse(this);
        }
    }
}
