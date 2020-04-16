package echoserver;

import java.io.IOException;

public class Echoer implements Echoable {
    private String message;

    public Echoer(String message) {
        this.message = message;
    }

    public void echo(Connection connection) throws IOException {
        String input;
        connection.write(this.message);
        while ((input = connection.read()) != null) {
            connection.write(input);
        }
    }
}
