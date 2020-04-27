package httpserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpPresenterTest {
    @Test
    void itFormatsAnHttpResponseWithOnlyAResponseLine() {
        HttpPresenter presenter = new HttpPresenter();
        HttpResponse response = new HttpResponse();
        response.setResponseLine("200 Ok");

        String formattedResponse = (
                "HTTP/1.1 200 Ok" +
                        System.lineSeparator() +
                        System.lineSeparator()
                );

        assertEquals(formattedResponse, presenter.present(response));
    }

    @Test
    void itFormatsAnHttpResponseWithAHeader() {
        HttpPresenter presenter = new HttpPresenter();
        HttpResponse response = new HttpResponse();
        response.setResponseLine("405 Method Not Allowed");
        response.setHeaders("Allow: HEAD, OPTIONS");

        String formattedResponse = (
                "HTTP/1.1 405 Method Not Allowed" +
                        System.lineSeparator() +
                        "Allow: HEAD, OPTIONS" +
                        System.lineSeparator() +
                        System.lineSeparator()
                );

        assertEquals(formattedResponse, presenter.present(response));
    }

    @Test
    void itFormatsAnHttpResponseWithABody() {
        HttpPresenter presenter = new HttpPresenter();
        HttpResponse response = new HttpResponse();
        response.setResponseLine("200 Ok");
        response.setBody("Hello world");

        String formattedResponse = (
                "HTTP/1.1 200 Ok" +
                        System.lineSeparator() +
                        System.lineSeparator() +
                        "Hello world"
                );

        assertEquals(formattedResponse, presenter.present(response));
    }
}