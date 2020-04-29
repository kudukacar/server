package httpserver;

public class HttpPresenter implements Presentable{

    public String present(HttpResponse response) {
        String responseLine = response.getResponseLine() + System.lineSeparator();
        return responseLine + headers(response) + body(response);
    }

    private String body(HttpResponse response) {
        String body = response.getBody();
        if(body == null) {
            return "";
        } else {
            return body;
        }
    }

    private String headers(HttpResponse response) {
        if(response.getHeaders() == null) {
            return System.lineSeparator();
        } else {
            return String.join(System.lineSeparator(),response.getHeaders()) +
                    System.lineSeparator() +
                    System.lineSeparator();
        }
    }
}
