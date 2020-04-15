package echoserver;

import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {
    @Test
    void itLogsAMessage() {
        FakePrintStream printStream = new FakePrintStream(System.out);
        Logger logger = new Logger(printStream);
        String error = "There was an error";

        logger.log(error);

        assertEquals(error, printStream.logged);
    }


    private class FakePrintStream extends PrintStream {
        public String logged;

        public FakePrintStream(OutputStream out) {
            super(out);
        }

        @Override
        public void println(String x) {
            logged = x;
        }
    }
}