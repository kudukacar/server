package echoserver;

import java.io.IOException;

public interface Echoable {
    void echo(Connection connection) throws IOException;
}
