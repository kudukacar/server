package echoserver;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EchoServerTest {
    @Test
    void itEchosText() throws IOException {
        EchoServer echoServer = new EchoServer();
        ArrayList<String> input = new ArrayList<String>(Arrays.asList("This", "Should", "be", "echoed"));
        TestConnection connection = new TestConnection(input);
        echoServer.echo(connection);

        assertEquals(Arrays.asList("This", "Should", "be", "echoed"), connection.written);
    }

    @Test
    void itClosesTheConnection() throws IOException {
        EchoServer echoServer = new EchoServer();
        ArrayList<String> input = new ArrayList<String>(Arrays.asList("This", "Should", "be", "echoed"));
        TestConnection connection = new TestConnection(input);
        echoServer.echo(connection);

        assertTrue(connection.closed);
    }

    @Test
    void itClosesTheConnectionWhenAnExceptionIsRaised() throws IOException {
        EchoServer echoServer = new EchoServer();
        ExceptionalConnection connection = new ExceptionalConnection();

        try {
            echoServer.echo(connection);
        } catch (Exception e) {

        }
        assertTrue(connection.closed);

    }
    private class ExceptionalConnection implements Connection {

        private boolean closed;

        public ExceptionalConnection() {
            this.closed = false;
        }

        @Override
        public String read() throws IOException {
            throw new RuntimeException("Unable to read");
        }

        @Override
        public void write(String output) throws IOException {

        }

        @Override
        public void close() throws IOException {
            this.closed = true;
        }
    }


    private class TestConnection implements Connection {

        public ArrayList<String> written;
        public boolean closed;
        private ArrayList<String> input;

        public TestConnection(ArrayList<String> input) {
            this.input = input;
            this.written = new ArrayList<String>();
            this.closed = false;
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
        public void close() throws IOException {
            this.closed = true;
        }
    }
}