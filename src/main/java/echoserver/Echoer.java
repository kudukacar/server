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
        try {
            connection.write(this.message);
            while ((input = connection.read()) != null) {
                connection.write(input);
            }
        } catch (IOException e) {
            logger.log("Failed to read from connection.");
        } finally {
            connection.close();
        }
    }
}
