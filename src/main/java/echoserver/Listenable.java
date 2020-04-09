package echoserver;

import java.io.IOException;

public interface Listenable {
    Connection open() throws IOException;
    void close() throws IOException;
}
