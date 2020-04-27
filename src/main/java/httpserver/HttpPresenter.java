package httpserver;

public class HttpPresenter implements Presentable{

    public String present(HttpResponse response) {
        String responseLine = response.getResponseLine() + System.lineSeparator();
        String body = response.getBody();
        return responseLine + headers(response) + body;
    }

    private String headers(HttpResponse response) {
        if(response.getHeaders().isEmpty()) {
            return System.lineSeparator();
        } else {
            return String.join(System.lineSeparator(),response.getHeaders()) +
                    System.lineSeparator() +
                    System.lineSeparator();
        }
    }
}
