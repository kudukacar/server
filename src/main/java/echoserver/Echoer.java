package echoserver;

import infrastructure.Connection;
import infrastructure.Respondable;

import java.io.IOException;

public class Echoer implements Respondable {
    private String message;

    public Echoer(String message) {
        this.message = message;
    }

    public void respond(Connection connection) throws IOException {
        String input;
        connection.write(this.message);
        while ((input = connection.read()) != null) {
            connection.write(input + System.lineSeparator());
        }
    }
}
