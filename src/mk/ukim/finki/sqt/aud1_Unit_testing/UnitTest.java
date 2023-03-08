package mk.ukim.finki.sqt.aud1_Unit_testing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    String message = "Hello World!";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testPrintMessage() {
        assertEquals(message, messageUtil.printMessage());
    }

    @Test
    public void testPrintMessage2() {
        message = "New Word";
        assertEquals(message, messageUtil.printMessage());
    }
}
