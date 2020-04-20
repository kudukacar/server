package echoserver;

import infrastructure.Connection;

import java.io.IOException;

public interface Echoable {
    void echo(Connection connection) throws IOException;
}
