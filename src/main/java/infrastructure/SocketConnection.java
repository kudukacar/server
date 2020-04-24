package infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketConnection implements Connection {
    private final Socket socket;

    public SocketConnection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String read() throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
    }

    @Override
    public void write(String output) throws IOException {
        socket.getOutputStream().write(output.getBytes());
    }

    @Override
    public void close() throws IOException {
        this.socket.shutdownOutput();
    }

    public boolean isClosed() {
        return this.socket.isOutputShutdown();
    }
}
