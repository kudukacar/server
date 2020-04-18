package echoserver;

import Infrastructure.Connection;

import java.io.IOException;

public interface Echoable {
    void echo(Connection connection) throws IOException;
}
