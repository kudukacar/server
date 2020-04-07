package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientServerConnection implements Connection{
    public final Socket socket;

    public ClientServerConnection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String read() throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
    }

    @Override
    public void write(String output) throws IOException {
        String formattedOutput = output + "\n";
        socket.getOutputStream().write(formattedOutput.getBytes());
    }

    @Override
    public void close() throws IOException {
        this.socket.close();
    }
}
