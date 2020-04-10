package echoserver;

import java.io.IOException;

public class EchoServer {
    private final Openable listener;
    private final Echoable echoClient;

    public EchoServer(Openable listener, Echoable echoer) {
        this.listener = listener;
        this.echoClient = echoer;
    }

    public void start() throws Exception {
        Connection socketConnection;
        while((socketConnection = this.listener.open()) != null) {
            this.echoClient.echo(socketConnection);
        }
    }
}
