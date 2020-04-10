package echoserver;

import java.io.IOException;

public class EchoServer {
    private final Openable listener;
    private final Echoable echoer;

    public EchoServer(Openable listener, Echoable echoer) {
        this.listener = listener;
        this.echoer = echoer;
    }

    public void start() throws Exception {
        Connection socketConnection;
        while((socketConnection = this.listener.open()) != null) {
            this.echoer.echo(socketConnection);
        }
    }
}
