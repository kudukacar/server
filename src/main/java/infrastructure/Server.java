package infrastructure;

import java.io.IOException;
import java.util.concurrent.Executor;

public class Server {
    private final Listenable listener;
    private final Respondable echoer;
    private final Executor executor;
    private final Loggable logger;

    public Server(Listenable listener, Executor executor, Respondable echoer, Loggable logger) {
        this.listener = listener;
        this.echoer = echoer;
        this.executor = executor;
        this.logger = logger;
    }

    public void start() throws Exception {
        Connection socketConnection;
        while((socketConnection = this.listener.listen()) != null) {
            Runnable runnable = runnable(socketConnection);
            executor.execute(runnable);
        }
    }

    private Runnable runnable(Connection connection) {
        Runnable runnable = () -> {
            try (Connection socketConnection = connection) {
                this.echoer.respond(socketConnection);
            } catch (IOException e) {
                this.logger.log("Failed to read from or write to the socket connection: " + e);
            }
        };
        return runnable;
    }
}
