package infrastructure;

import java.io.IOException;

public interface Respondable {
    void respond(Connection connection) throws IOException;
}
