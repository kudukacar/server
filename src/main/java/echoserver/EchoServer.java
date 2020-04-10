package echoserver;

public class EchoServer {
    private final Listenable listener;
    private final Echoable echoer;

    public EchoServer(Listenable listener, Echoable echoer) {
        this.listener = listener;
        this.echoer = echoer;
    }

    public void start() throws Exception {
        Connection socketConnection;
        while((socketConnection = this.listener.listen()) != null) {
            this.echoer.echo(socketConnection);
        }
    }
}
