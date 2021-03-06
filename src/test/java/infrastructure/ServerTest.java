package infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServerTest {
    Respondable echoer;
    TestConnection firstConnection;
    TestConnection secondConnection;
    TestConnection thirdConnection;
    ArrayList<TestConnection> connections;
    FakeListener listener;
    SerialExecutor executor;
    ErrorLogger logger;
    Server server;

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
        server = new Server(listener, executor, echoer, logger);

        server.start();

        assertEquals(Arrays.asList(firstConnection, secondConnection, thirdConnection),listener.connectedClients);
    }

    @Test
    void itExecutesTheRunnableForEachConnection() throws Exception {
        echoer = new EchoOnlyOnce();
        server = new Server(listener, executor, echoer, logger);

        server.start();

        assertEquals(Arrays.asList("executed", "executed", "executed"), executor.executed);
    }

    @Test
    void itEchoesEachConnection() throws Exception {
        echoer = new EchoOnlyOnce();
        server = new Server(listener, executor, echoer, logger);

        server.start();

        assertEquals("echoechoecho", firstConnection.writes + secondConnection.writes + thirdConnection.writes);
    }

    @Test
    void itLogsAMessageWhenAnExceptionIsRaised() throws Exception {
        echoer = new ExceptionalEchoer();
        server = new Server(listener, executor, echoer, logger);

        server.start();

        assertTrue(logger.logged.contains("read"));
    }

    @Test
    void itClosesTheConnection() throws Exception {
        echoer = new EchoOnlyOnce();
        server = new Server(listener, executor, echoer, logger);

        server.start();

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

    private class EchoOnlyOnce implements Respondable {
        @Override
        public void respond(Connection connection) throws IOException {
            connection.write("echo");
        }
    }

    private class ExceptionalEchoer implements Respondable {
        @Override
        public void respond(Connection connection) throws IOException {
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

    private class ErrorLogger implements Loggable {
        String logged = "";
        @Override
        public void log(String error) {
            logged = error;
        }
    }
}