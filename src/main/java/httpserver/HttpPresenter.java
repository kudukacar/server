package httpserver;

public class HttpPresenter implements Presentable{

    public String present() {
        return "HTTP/1.1 405 Method Not Allowed" + System.lineSeparator() + "Allow: HEAD, OPTIONS" + System.lineSeparator();
    }
}
