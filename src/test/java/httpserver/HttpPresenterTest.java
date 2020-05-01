package httpserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpPresenterTest {
    @Test
    void itFormatsAnHttpResponseWithOnlyAResponseLine() {
        HttpPresenter presenter = new HttpPresenter();
        HttpResponse response = new HttpResponse.Builder()
                .status(HttpStatus.OK)
                .build();

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
        HttpResponse response = new HttpResponse.Builder()
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .addHeader("Allow: HEAD, OPTIONS")
                .build();

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
        HttpResponse response = new HttpResponse.Builder()
                .status(HttpStatus.OK)
                .body("Hello world")
                .build();

        String formattedResponse = (
                "HTTP/1.1 200 Ok" +
                        System.lineSeparator() +
                        System.lineSeparator() +
                        "Hello world"
                );

        assertEquals(formattedResponse, presenter.present(response));
    }
}