package httpserver;

public interface Controller {
    HttpResponse control(String httpRequest);
}
