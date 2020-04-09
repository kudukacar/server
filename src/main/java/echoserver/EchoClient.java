package echoserver;

import java.io.IOException;

public class EchoClient implements Client{
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
