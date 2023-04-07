package mk.ukim.finki.sqt.auds.aud1_Unit_testing;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Unit2Test {
    String message = "Robert";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testSalutationMessage() {
        System.out.println("Inside testSalutationMessage()");
        message = "Hi!" + "Robert";
        assertEquals(message, messageUtil.salutationMessage());
    }

    @Test
    public void testMessageUtilObject() {
        assertNotNull(messageUtil);
    }
}
