package mk.ukim.finki.sqt.labs.lab_4;


import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;


public class EmployeeTest {

    private Employee employee = new Employee();

    //1, 5, 7, 8 - RACC
    public static Collection<Object[]> randomEmployees() {
        return Arrays.asList(new Object[][]{
                {new Employee(true, true, 6)},
                {new Employee(false, true, 6)},
                {new Employee(false, false, 6)},
                {new Employee(false, false, 3)},
        });
    }

    @ParameterizedTest
    @MethodSource("randomEmployees")
    public void testEmployeesGettingBonus(Employee other) {
        if (other.isSenior || !other.isPartTime && other.monthsInCompany >= 6) {
            Assert.assertEquals(true, this.employee.getBonus(other));
        } else {
            Assert.assertEquals(false, this.employee.getBonus(other));
        }
    }

}
