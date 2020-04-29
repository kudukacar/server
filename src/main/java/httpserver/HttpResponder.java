package httpserver;

import infrastructure.Connection;
import infrastructure.Respondable;

import java.io.IOException;

public class HttpResponder implements Respondable {
    private final Routeable router;
    private final Parseable parser;
    private final Presentable presenter;

    public HttpResponder(Parseable parser, Routeable router, Presentable presenter) {
        this.parser = parser;
        this.router = router;
        this.presenter = presenter;
    }

    @Override
    public void respond(Connection connection) throws IOException {
        String input = connection.read();
        HttpRequest parsedInput = parser.parse(input);
        HttpResponse response = router.route(parsedInput);
        String formattedResponse = presenter.present(response);
        connection.write(formattedResponse);
    }
}
