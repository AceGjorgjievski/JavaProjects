package mk.ukim.finki.sqt.auds.aud_2_Unit_testing_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.Assert.assertThrows;

public class DivisionTest {
    private Division division;

    @BeforeEach
    public void init() {
        this.division = new Division();
    }

    @Test
    public void testDivision() {
        assertThrows(ArithmeticException.class, () -> this.division.divide(12,12));
    }

    @Test
    public void testDivision2() {
        assertEquals(2, this.division.divide(4,2));
    }
}
