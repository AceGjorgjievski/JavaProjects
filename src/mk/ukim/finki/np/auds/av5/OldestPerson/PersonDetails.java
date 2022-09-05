package mk.ukim.finki.np.auds.av5.OldestPerson;

public class PersonDetails implements Comparable<PersonDetails>{
    private String name;
    private int age;

    public PersonDetails(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public PersonDetails(String line) {
        String [] parts = line.split("\\s++");
        this.name = parts[0];
        this.age = Integer.parseInt(parts[1]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(PersonDetails other) {
        return Integer.compare(this.age, other.age);
    }

    @Override
    public String toString() {
        return "PersonDetails{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
