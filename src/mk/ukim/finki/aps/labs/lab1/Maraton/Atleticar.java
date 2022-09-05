package mk.ukim.finki.aps.labs.lab1.Maraton;

import java.util.Objects;

public class Atleticar {
    private String name;
    private String gender;
    private int age;
    private double timeRunning;
    private String country;

    public Atleticar() {
    }

    public Atleticar(String name, String gender, int age, double timeRunning, String country) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.timeRunning = timeRunning;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getTimeRunning() {
        return timeRunning;
    }

    public void setTimeRunning(double timeRunning) {
        this.timeRunning = timeRunning;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atleticar atleticar = (Atleticar) o;
        return age == atleticar.age && Double.compare(atleticar.timeRunning, timeRunning) == 0 && Objects.equals(name, atleticar.name) && Objects.equals(gender, atleticar.gender) && Objects.equals(country, atleticar.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, age, timeRunning, country);}

    @Override
    public String toString() {
        return name + "\n" + age + "\n" + country + "\n" + timeRunning;
    }
}

