package httpserver;

import infrastructure.Connection;
import infrastructure.Respondable;

import java.io.IOException;

public class HttpResponder implements Respondable {
    private final Controller controller;
    private final Parseable parser;
    private Presentable presenter;

    public HttpResponder(Parseable parser, Controller controller, Presentable presenter) {
        this.parser = parser;
        this.controller = controller;
        this.presenter = presenter;
    }

    @Override
    public void respond(Connection connection) throws IOException {
        String input = connection.read();
        String parsedInput = parser.parse(input);
        HttpResponse response = controller.control(parsedInput);
        String formattedResponse = presenter.present(response);
        connection.write(formattedResponse);
    }
}
