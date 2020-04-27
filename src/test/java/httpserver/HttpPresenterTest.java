package httpserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpPresenterTest {
    @Test
    void itFormatsAnHttpResponseWithOnlyAResponseLine() {
        HttpPresenter presenter = new HttpPresenter();
        HttpResponse response = new HttpResponse();
        response.responseLine += "200 Ok";
        String responseLine = "HTTP/1.1 200 Ok" + System.lineSeparator();
        String header = System.lineSeparator();
        String body = "";
        String formattedResponse = responseLine + header + body;

        assertEquals(presenter.present(response), formattedResponse);
    }

    @Test
    void itFormatsAnHttpResponseWithAHeader() {
        HttpPresenter presenter = new HttpPresenter();
        HttpResponse response = new HttpResponse();
        response.responseLine += "405 Method Not Allowed";
        response.headers.add("Allow: HEAD, OPTIONS");
        String responseLine = "HTTP/1.1 405 Method Not Allowed" + System.lineSeparator();
        String header = "Allow: HEAD, OPTIONS" + System.lineSeparator();
        String blankLine = System.lineSeparator();
        String body = "";
        String formattedResponse = responseLine + header + blankLine + body;

        assertEquals(presenter.present(response), formattedResponse);
    }

    @Test
    void itFormatsAnHttpResponseWithABody() {
        HttpPresenter presenter = new HttpPresenter();
        HttpResponse response = new HttpResponse();
        response.responseLine += "200 Ok";
        response.body = "Hello world";
        String responseLine = "HTTP/1.1 200 Ok" + System.lineSeparator();
        String header = System.lineSeparator();
        String body = "Hello world";
        String formattedResponse = responseLine + header + body;

        assertEquals(presenter.present(response), formattedResponse);
    }
}