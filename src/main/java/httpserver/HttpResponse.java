package httpserver;

import java.util.ArrayList;
import java.util.List;

public class HttpResponse {
    private String responseLine = "HTTP/1.1 ";
    private String body = "";
    private List<String> headers = new ArrayList<String>();

    public String getResponseLine() {
        return this.responseLine;
    }

    public void setResponseLine(String status) {
        this.responseLine += status;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(String header) {
        this.headers.add(header);
    }
}
