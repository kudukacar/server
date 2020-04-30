package httpserver;

import java.util.ArrayList;
import java.util.List;

public class HttpResponse {
    private final String responseLine;
    private final String body;
    private final List<String> headers;

    private HttpResponse(Builder builder) {
        this.responseLine = "HTTP/1.1 " + builder.statusCode + " " + builder.statusName;
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
        private String body = "";
        private List<String> headers = new ArrayList<>();
        private String statusCode;
        private String statusName;

        public Builder statusCode (String statusCode) {
            this.statusCode = statusCode;
            return this;
        }
        public Builder statusName (String statusName) {
            this.statusName = statusName;
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
