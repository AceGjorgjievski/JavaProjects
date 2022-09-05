package mk.ukim.finki.np.pochetok;

import java.util.Objects;

class  Employee {
    private String firstName;
    private String lastName;
    private double salary;
    private static double MINIMAL_SALARY = 10000.0;


    public Employee(String firstName, String lastName, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = MINIMAL_SALARY;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static void setMinimalSalary(double minimalSalary) {
        MINIMAL_SALARY = minimalSalary;
    }

    public static double getMinimalSalary() {
        return MINIMAL_SALARY;
    }
    //nasleden metod koj shto se naogja vo klasata shto site klasi nasleduvaaat
    // vo java a toa e klasata object
    public String toString() { // operator << (print an object from this class)
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 &&
                firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, salary);
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Ace","Gjorgjievski");
        System.out.println(e1);

        Employee e2 = e1;
        e2.setSalary(25000.0);
       // Employee e2 = new Employee("Ace","Gjorgjievski",10000.0);
        System.out.println(e1 == e2);
        System.out.println(e1);
        System.out.println(e2);
    }
}

