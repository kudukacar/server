package infrastructure;

import java.io.PrintStream;

public class Logger implements Loggable {
    private final PrintStream out;

    public Logger(PrintStream out) {
        this.out = out;
    }

    public void log(String error) {
        this.out.println(error);
    }
}
