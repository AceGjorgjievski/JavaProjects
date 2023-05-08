package mk.ukim.finki.sqt.labs.lab_4;

public class Employee {
    boolean isSenior;
    boolean isPartTime;
    int monthsInCompany = 3;

    public Employee() {
    }

    public Employee(boolean isSenior, boolean isPartTime, int monthsInCompany) {
        this.isSenior = isSenior;
        this.isPartTime = isPartTime;
        this.monthsInCompany = monthsInCompany;
    }

    public void makeSenior() {
        isSenior = true;
        if (isPartTime && monthsInCompany >= 6) {
            monthsInCompany = 3;
        }
    }

    public void makePartTime() {
        isPartTime = true;
        if (isSenior && monthsInCompany >= 6) {
            isSenior = false;
        }
    }

    public void setMonthsInCompany() {
        monthsInCompany = 6;
        if (isSenior && isPartTime) {
            isPartTime = false;
        }
    }

    public void notSenior() {
        isSenior = false;
    }

    public void notPartTime() {
        isPartTime = false;
    }

    public void lessThan6Months() {
        monthsInCompany = 3;
    }

    public boolean getBonus(Employee employee) {
        return employee.isSenior || (!employee.isPartTime && employee.monthsInCompany >= 6);
    }
}
