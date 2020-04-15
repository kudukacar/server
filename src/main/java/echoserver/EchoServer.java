package echoserver;

import java.io.IOException;
import java.util.concurrent.Executor;

public class EchoServer {
    private final Listenable listener;
    private final Echoable echoer;
    private final Executor executor;
    private final Loggable logger;

    public EchoServer(Listenable listener, Executor executor, Echoable echoer, Loggable logger) {
        this.listener = listener;
        this.echoer = echoer;
        this.executor = executor;
        this.logger = logger;
    }

    public void start() throws Exception {
        Connection socketConnection;
        while((socketConnection = this.listener.listen()) != null) {
            Connection finalSocketConnection = socketConnection;
            Runnable runnable = () -> {
                try {
                    this.echoer.echo(finalSocketConnection);
                } catch (IOException e) {
                    this.logger.log("Failed to echo connection.");
                }
            };
            executor.execute(runnable);
        }
    }
}
