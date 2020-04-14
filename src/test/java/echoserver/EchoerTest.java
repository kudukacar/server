package echoserver;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EchoerTest {
    @Test
    void itEchosTextAfterWelcomingTheUser() throws IOException {
        errorLogger logger = new errorLogger();
        Echoer echoer = new Echoer("Welcome", logger);
        ArrayList<String> input = new ArrayList<String>(Arrays.asList("This", "Should", "be", "echoed"));
        TestConnection connection = new TestConnection(input);
        echoer.echo(connection);

        assertEquals(Arrays.asList("Welcome", "This", "Should", "be", "echoed"), connection.written);
    }

    @Test
    void itLogsAMessageWhenAnExceptionIsRaised() throws IOException {
        errorLogger logger = new errorLogger();
        Echoer echoer = new Echoer("Welcome", logger);
        ExceptionalConnection connection = new ExceptionalConnection();

        echoer.echo(connection);

        assertTrue(logger.logged.contains("read"));
    }

    @Test
    void itClosesTheConnectionWhenAnExceptionIsRaised() throws IOException {
        errorLogger logger = new errorLogger();
        Echoer echoer = new Echoer("Welcome", logger);
        ExceptionalConnection connection = new ExceptionalConnection();

        echoer.echo(connection);

        assertTrue(connection.isClosed());

    }

    private class ExceptionalConnection implements Connection {

        private boolean closed;

        public ExceptionalConnection() {
            this.closed = false;
        }

        @Override
        public String read() throws IOException {
            throw new IOException("Unable to read");
        }

        @Override
        public void write(String output) {

        }

        @Override
        public void close() {
            this.closed = true;
        }

        @Override
        public boolean isClosed() {
            return this.closed;
        }
    }


    private class TestConnection implements Connection {

        public ArrayList<String> written;
        private ArrayList<String> input;

        public TestConnection(ArrayList<String> input) {
            this.input = input;
            this.written = new ArrayList<String>();
        }

        @Override
        public String read() {
            if(this.input.size() > 0) {
                return this.input.remove(0);
            } else {
                return null;
            }
        }

        @Override
        public void write(String output) {
            this.written.add(output);
        }

        @Override
        public void close() {
        }

        @Override
        public boolean isClosed() {
            return false;
        }
    }

    private class errorLogger implements Loggable{
        String logged = "";
        @Override
        public void log(String error) {
            logged = error;
        }
    }
}