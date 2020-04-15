package echoserver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class EchoServerTest {
    Echoable echoer;
    TestConnection firstConnection;
    TestConnection secondConnection;
    TestConnection thirdConnection;
    ArrayList<TestConnection> connections;
    FakeListener listener;
    SerialExecutor executor;
    ErrorLogger logger;
    EchoServer echoServer;

    @BeforeEach
    void setUp() {
        firstConnection = new TestConnection();
        secondConnection = new TestConnection();
        thirdConnection = new TestConnection();
        connections = new ArrayList<TestConnection>(Arrays.asList(firstConnection, secondConnection, thirdConnection));
        listener = new FakeListener(connections);
        executor = new SerialExecutor();
        logger = new ErrorLogger();
    }

    @Test
    void itHandlesMultipleConnections() throws Exception {
        echoer = new EchoOnlyOnce();
        echoServer = new EchoServer(listener, executor, echoer, logger);

        echoServer.start();

        assertEquals(Arrays.asList(firstConnection, secondConnection, thirdConnection),listener.connectedClients);
    }

    @Test
    void itExecutesTheRunnableForEachConnection() throws Exception {
        echoer = new EchoOnlyOnce();
        echoServer = new EchoServer(listener, executor, echoer, logger);

        echoServer.start();

        assertEquals(Arrays.asList("executed", "executed", "executed"), executor.executed);
    }

    @Test
    void itEchoesEachConnection() throws Exception {
        echoer = new EchoOnlyOnce();
        echoServer = new EchoServer(listener, executor, echoer, logger);

        echoServer.start();

        String expected = connections
                .stream()
                .map(c -> c.writes)
                .collect(Collectors.joining(""));

        assertEquals("echoechoecho", firstConnection.writes + secondConnection.writes + thirdConnection.writes);
    }

    @Test
    void itLogsAMessageWhenAnExceptionIsRaised() throws Exception {
        echoer = new ExceptionalEchoer();
        echoServer = new EchoServer(listener, executor, echoer, logger);

        echoServer.start();

        assertTrue(logger.logged.contains("read"));
    }

    @Test
    void itClosesTheConnection() throws Exception {
        echoer = new EchoOnlyOnce();
        echoServer = new EchoServer(listener, executor, echoer, logger);

        echoServer.start();

        assertTrue(firstConnection.isClosed());
    }

    private class TestConnection implements Connection {


        public String writes = null;
        private boolean closed = false;

        @Override
        public String read() {
            return null;
        }

        @Override
        public void write(String output) {
            this.writes = output;
        }

        @Override
        public void close() {
            closed = true;
        }

        @Override
        public boolean isClosed() {
            return closed;
        }
    }

    private class EchoOnlyOnce implements Echoable {
        @Override
        public void echo(Connection connection) throws IOException {
            connection.write("echo");
        }
    }

    private class ExceptionalEchoer implements Echoable {
        @Override
        public void echo(Connection connection) throws IOException {
            throw new IOException("Unable to echo.");
        }
    }

    private class FakeListener implements Listenable {
        private ArrayList<TestConnection> clients;
        public ArrayList<TestConnection> connectedClients;

        public FakeListener(ArrayList<TestConnection> clients) {
            this.clients = clients;
            this.connectedClients = new ArrayList<TestConnection>();
        }

        @Override
        public Connection listen() {
            if(this.clients.size() > 0) {
                TestConnection client = this.clients.remove(0);
                this.connectedClients.add(client);
                return client;
            } else return null;
        }

        @Override
        public void close() {

        }
    }

    private class SerialExecutor implements Executor {
        public ArrayList<String> executed = new ArrayList<String>();
        @Override
        public void execute(Runnable runnable) {
            executed.add("executed");
            runnable.run();
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