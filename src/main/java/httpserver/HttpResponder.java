package httpserver;

import infrastructure.Connection;
import infrastructure.Respondable;

import java.io.IOException;

public class HttpResponder implements Respondable {
    private Presentable presenter;

    public HttpResponder(Presentable presenter) {
        this.presenter = presenter;
    }

    @Override
    public void respond(Connection connection) throws IOException {
        connection.write(presenter.present());
    }
}
