package echoserver;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EchoServerTest {
    @Test
    void itConnectsToClientsInOrderReceived() throws IOException {
        FakeEchoClient echoClient = new FakeEchoClient();
        ClientSocketConnection first_client = new ClientSocketConnection();
        ClientSocketConnection second_client = new ClientSocketConnection();
        ClientSocketConnection third_client = new ClientSocketConnection();
        ArrayList<ClientSocketConnection> clients = new ArrayList<ClientSocketConnection>(Arrays.asList(first_client, second_client, third_client));
        FakeListener listener = new FakeListener(clients);
        EchoServer echoServer = new EchoServer(listener, echoClient);

        echoServer.connect();

        assertEquals(Arrays.asList(first_client, second_client, third_client),listener.connectedClients);
    }

    @Test
    void itClosesTheConnection() throws IOException {
        FakeEchoClient echoClient = new FakeEchoClient();
        ExceptionalListener listener = new ExceptionalListener();
        EchoServer echoServer = new EchoServer(listener, echoClient);

        echoServer.connect();

        assertTrue(listener.closed);
    }

    private class ClientSocketConnection implements Connection {

        @Override
        public String read() throws IOException {
            return null;
        }

        @Override
        public void write(String output) throws IOException {

        }

        @Override
        public void close() throws IOException {

        }

        @Override
        public boolean isClosed() {
            return false;
        }
    }

    private class FakeEchoClient implements Client{
        @Override
        public void echo(Connection connection) {
        }
    }

    private class ExceptionalListener implements Listenable {

        public boolean closed;

        public ExceptionalListener() {
            this.closed = false;
        }

        @Override
        public Connection open() {
            return null;
        }

        @Override
        public void close() {
            this.closed = true;
        }
    }

    private class FakeListener implements Listenable{
        private ArrayList<ClientSocketConnection> clients;
        public ArrayList<ClientSocketConnection> connectedClients;

        public FakeListener(ArrayList<ClientSocketConnection> clients) {
            this.clients = clients;
            this.connectedClients = new ArrayList<ClientSocketConnection>();
        }

        @Override
        public Connection open() {
            if(this.clients.size() > 0) {
                ClientSocketConnection client = this.clients.remove(0);
                this.connectedClients.add(client);
                return client;
            } else return null;
        }

        @Override
        public void close() {

        }
    }
}