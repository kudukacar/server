package httpserver;

import java.util.ArrayList;
import java.util.List;

public class HttpResponse {
    private final String body;
    private final List<String> headers;
    private final String version = "HTTP/1.1";
    private final String statusCode;
    private final String statusName;

    private HttpResponse(Builder builder) {
        this.statusCode = builder.statusCode;
        this.statusName = builder.statusName;
        this.body = builder.body;
        this.headers = builder.headers;
    }

    public String getVersion() {
        return version;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusName() {
        return statusName;
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
