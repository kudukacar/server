package httpserver;

public class HttpPresenter implements Presentable{

    public String present() {
        String responseLine = "HTTP/1.1 405 Method Not Allowed" + System.lineSeparator();
        String header = "Allow: HEAD, OPTIONS" + System.lineSeparator();
        String blankLine = System.lineSeparator();
        String body = new String(new byte[0]);
        return responseLine + header + blankLine + body;
    }
}
