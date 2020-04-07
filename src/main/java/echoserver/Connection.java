package echoserver;

import java.io.IOException;

public interface Connection {
    String read() throws IOException;

    void write(String output) throws IOException;
}
