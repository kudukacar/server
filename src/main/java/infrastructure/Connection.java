package infrastructure;

import java.io.IOException;

public interface Connection extends AutoCloseable{
    String read() throws IOException;

    void write(String output) throws IOException;

    public void close() throws IOException;

    public boolean isClosed() throws IOException;
}
