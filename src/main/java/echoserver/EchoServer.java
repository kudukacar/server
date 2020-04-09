package echoserver;

import java.io.IOException;

public class EchoServer {
    private final Listenable listener;
    private final Client echoClient;

    public EchoServer(Listenable listener, Client echoClient) {
        this.listener = listener;
        this.echoClient = echoClient;
    }

    public void connect() throws IOException {
        Connection socketConnection;

        try {
            while((socketConnection = this.listener.open()) != null) {
                this.echoClient.echo(socketConnection);
            }
        } finally {
            listener.close();
        }
    }
}
