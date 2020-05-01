package httpserver;

import java.util.ArrayList;
import java.util.List;

public class HttpResponse {
    private final String body;
    private final List<String> headers;
    private final String status;

    private HttpResponse(Builder builder) {
        this.status = builder.status;
        this.body = builder.body;
        this.headers = builder.headers;
    }

    public String getVersion() {
        return "HTTP/1.1";
    }

    public String getStatus() {
        return status;
    }

    public String getBody() {
        return this.body;
    }

    public List<String> getHeaders() {
        return this.headers;
    }

    public static class Builder {
        private String body = "";
        private final List<String> headers = new ArrayList<>();
        private String status;

        public Builder status(String status) {
            this.status = status;
            return this;
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
