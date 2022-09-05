package mk.ukim.finki.np.labs.kol2.audition;

import org.jetbrains.annotations.NotNull;

import java.util.*;


class Participant  {
    private String name;
    private int age;
    private String code;
    private String city;

    public Participant(String city,String code, String name, int age) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return String.format("%s %s %d",code,name,age);
    }
}

class Audition {
    private Map<String, Set<Participant>> map;

    public Audition() {
        map = new HashMap<>();
    }

    public void addParticpant(String city, String code, String name, int age) {
        Participant participant = new Participant(city, code, name, age);
        if(!map.containsKey(city)) {
            map.put(city,new HashSet<>());
        }
        if(!map.get(city).contains(participant))
            map.get(city).add(participant);
    }

    public void listByCity(String city) {
        Set<Participant> sortedSet = new TreeSet<>(Comparator.comparing(Participant::getName)
                .thenComparingInt(Participant::getAge)
                .thenComparing(Participant::getCode));
        sortedSet.addAll(map.get(city));

        sortedSet.stream()
                .forEach(i -> System.out.println(i));
    }
}

public class AuditionTest {
    public static void main(String[] args) {
        Audition audition = new Audition();
        List<String> cities = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length > 1) {
                audition.addParticpant(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]));
            } else {
                cities.add(line);
            }
        }
        for (String city : cities) {
            System.out.printf("+++++ %s +++++\n", city);
            audition.listByCity(city);
        }
        scanner.close();
    }
}