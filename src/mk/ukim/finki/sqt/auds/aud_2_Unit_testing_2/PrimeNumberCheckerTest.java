package mk.ukim.finki.sqt.auds.aud_2_Unit_testing_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeNumberCheckerTest {
    private PrimeNumberChecker primeNumberChecker;


    @BeforeEach
    public void setup() {
        this.primeNumberChecker = new PrimeNumberChecker();
    }

    public static Collection<Object[]> primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, true},
                {6, false},
                {19, true},
                {22, false},
                {23, true}
        });
    }

    @ParameterizedTest
    @MethodSource("primeNumbers")
    public void testPrimerNumberChecker(int inputNumber, boolean expectedResult) {
        System.out.println("Parameterized Number is: " + inputNumber);
        assertEquals(expectedResult, this.primeNumberChecker.validate(inputNumber));
    }
}
