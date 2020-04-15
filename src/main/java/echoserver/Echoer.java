package echoserver;

import java.io.IOException;

public class Echoer implements Echoable {
    private final Loggable logger;
    private String message;

    public Echoer(String message, Loggable logger) {
        this.message = message;
        this.logger = logger;
    }

    public void echo(Connection connection) throws IOException {
        String input;
        connection.write(this.message);
        while ((input = connection.read()) != null) {
            connection.write(input);
        }
    }
}
