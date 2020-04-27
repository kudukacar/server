package httpserver;

import java.util.ArrayList;
import java.util.List;

public class HttpResponse {
    public String responseLine = "HTTP/1.1 ";
    public String body = "";
    public List<String> headers = new ArrayList<String>();
}
