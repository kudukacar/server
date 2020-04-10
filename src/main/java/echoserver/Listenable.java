package echoserver;

import java.io.IOException;

public interface Listenable extends AutoCloseable{
    Connection listen() throws IOException;
}
