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

        logger.log("hello");

        assertEquals("hello", printStream.logged);
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