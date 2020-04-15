package echoserver;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EchoerTest {
    @Test
    void itEchosTextAfterWelcomingTheUser() throws IOException {
        ErrorLogger logger = new ErrorLogger();
        Echoer echoer = new Echoer("Welcome", logger);
        ArrayList<String> input = new ArrayList<String>(Arrays.asList("This", "Should", "be", "echoed"));
        TestConnection connection = new TestConnection(input);
        echoer.echo(connection);

        assertEquals(Arrays.asList("Welcome", "This", "Should", "be", "echoed"), connection.written);
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

    private class ErrorLogger implements Loggable{
        String logged = "";
        @Override
        public void log(String error) {
            logged = error;
        }
    }
}