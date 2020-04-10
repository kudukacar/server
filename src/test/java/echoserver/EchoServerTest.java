package echoserver;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EchoServerTest {
    @Test
    void itConnectsToClientsInOrderReceived() throws Exception {
        FakeEchoClient echoClient = new FakeEchoClient();
        TestConnection firstClient = new TestConnection();
        TestConnection secondClient = new TestConnection();
        TestConnection thirdClient = new TestConnection();
        ArrayList<TestConnection> clients = new ArrayList<TestConnection>(Arrays.asList(firstClient, secondClient, thirdClient));
        FakeListener listener = new FakeListener(clients);
        EchoServer echoServer = new EchoServer(listener, echoClient);

        echoServer.start();

        assertEquals(Arrays.asList(firstClient, secondClient, thirdClient),listener.connectedClients);
    }

    private class TestConnection implements Connection {

        @Override
        public String read() {
            return null;
        }

        @Override
        public void write(String output) {

        }

        @Override
        public void close() {

        }

        @Override
        public boolean isClosed() {
            return false;
        }
    }

    private class FakeEchoClient implements Echoable {
        @Override
        public void echo(Connection connection) {
        }
    }

    private class FakeListener implements Openable {
        private ArrayList<TestConnection> clients;
        public ArrayList<TestConnection> connectedClients;

        public FakeListener(ArrayList<TestConnection> clients) {
            this.clients = clients;
            this.connectedClients = new ArrayList<TestConnection>();
        }

        @Override
        public Connection open() {
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
}