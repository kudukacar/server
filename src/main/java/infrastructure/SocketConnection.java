package infrastructure;

import java.io.*;
import java.net.Socket;
import java.util.stream.Collectors;

public class SocketConnection implements Connection {
    private final Socket socket;

    public SocketConnection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String read() throws IOException {
        InputStream inputStream = socket.getInputStream();
        StringBuilder input = new StringBuilder();
        do {
            input.append((char) inputStream.read());
        } while(inputStream.available() > 0);
        return input.toString();
    }

    @Override
    public void write(String output) throws IOException {
        socket.getOutputStream().write(output.getBytes());
    }

    @Override
    public void close() throws IOException {
        this.socket.shutdownOutput();
        this.socket.shutdownInput();
    }

    public boolean isClosed() {
        return this.socket.isOutputShutdown() && this.socket.isInputShutdown();
    }
}
