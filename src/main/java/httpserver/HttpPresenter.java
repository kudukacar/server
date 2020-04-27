package httpserver;

public class HttpPresenter implements Presentable{

    public String present(HttpResponse response) {
        String responseLine = response.responseLine + System.lineSeparator();
        String body = response.body;
        return responseLine + headers(response) + body;
    }

    private String headers(HttpResponse response) {
        if(response.headers.isEmpty()) {
            return System.lineSeparator();
        } else {
            return String.join(System.lineSeparator(),response.headers) + System.lineSeparator() + System.lineSeparator();
        }
    }
}
