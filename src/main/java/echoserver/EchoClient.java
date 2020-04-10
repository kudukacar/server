package echoserver;

import java.io.IOException;

public class EchoClient implements Echoable {
    public void echo(Connection connection) throws IOException {
        String input;
        try {
            while ((input = connection.read()) != null) {
                connection.write(input);
            }
        } finally {
            connection.close();
        }
    }
}
