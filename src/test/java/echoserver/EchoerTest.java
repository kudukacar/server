package echoserver;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EchoerTest {
    @Test
    void itEchosText() throws IOException {
        Echoer echoer = new Echoer();
        ArrayList<String> input = new ArrayList<String>(Arrays.asList("This", "Should", "be", "echoed"));
        TestConnection connection = new TestConnection(input);
        echoer.echo(connection);

        assertEquals(Arrays.asList("This", "Should", "be", "echoed"), connection.written);
    }

    @Test
    void itClosesTheConnectionWhenAnExceptionIsRaised() {
        Echoer echoer = new Echoer();
        ExceptionalConnection connection = new ExceptionalConnection();

        try {
            echoer.echo(connection);
        } catch (Exception e) {

        }
        assertTrue(connection.isClosed());

    }
    private class ExceptionalConnection implements Connection {

        private boolean closed;

        public ExceptionalConnection() {
            this.closed = false;
        }

        @Override
        public String read() {
            throw new RuntimeException("Unable to read");
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
}