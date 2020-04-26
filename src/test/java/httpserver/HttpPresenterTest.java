package httpserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpPresenterTest {
    @Test
    void itFormatsAnHttpResponse() {
        HttpPresenter presenter = new HttpPresenter();
        String responseLine = "HTTP/1.1 405 Method Not Allowed" + System.lineSeparator();
        String header = "Allow: HEAD, OPTIONS" + System.lineSeparator();
        String blankLine = System.lineSeparator();
        String body = "";
        String formattedResponse = responseLine + header + blankLine + body;

        assertEquals(presenter.present(), formattedResponse);
    }
}