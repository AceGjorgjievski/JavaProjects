package mk.ukim.finki.np.vezhbi.auds.citanjeBuffRead.oldestPerson;

import org.jetbrains.annotations.NotNull;

public class Person implements Comparable<Person>{
    private String name;
    private int age;

    public Person(String line) {
        String [] parts = line.split("\\s++");
        this.name = parts[0];
        this.age = Integer.parseInt(parts[1]);
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, age: %d",name,age);
    }

    @Override
    public int compareTo(@NotNull Person other) {
        return Integer.compare(this.age, other.age);
    }
}
