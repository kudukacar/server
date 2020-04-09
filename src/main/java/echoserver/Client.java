package echoserver;

import java.io.IOException;

public interface Client {
    void echo(Connection connection) throws IOException;
}
