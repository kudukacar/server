package echoserver;

import java.io.IOException;

public class EchoServer {
    public void echo(Connection connection) throws IOException {
        String input;
        while ((input = connection.read()) != null) {
            connection.write(input);
        }
    }
}
