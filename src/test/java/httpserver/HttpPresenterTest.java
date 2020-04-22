package httpserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpPresenterTest {
    @Test
    void itFormatsAnHttpResponse() {
        HttpPresenter presenter = new HttpPresenter();
        String formattedResponse = "HTTP/1.1 405 Method Not Allowed\nAllow: HEAD, OPTIONS\n";

        assertEquals(presenter.present(), formattedResponse);
    }
}