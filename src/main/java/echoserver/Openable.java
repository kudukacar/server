package echoserver;

import java.io.IOException;

public interface Openable extends AutoCloseable{
    Connection open() throws IOException;
}
