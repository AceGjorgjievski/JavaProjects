package mk.ukim.finki.np.vezhbi.za_juni.audition;

import java.util.*;

class Participant {
    String city;
    String code;
    String name;
    int age;

    public Participant(String city, String code, String name, int age) {
        this.city = city;
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
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
        return age == that.age && Objects.equals(city, that.city) && Objects.equals(code, that.code) && Objects.equals(name, that.name);
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
    Map<String, Set<Participant>> participantsByCity;

    public Audition() {
        participantsByCity = new HashMap<>();
    }

    public void addParticipant(String city, String code, String name, int age) {
        Participant p = new Participant(city, code, name, age);
        if (participantsByCity.containsKey(city)) {
            participantsByCity.get(city).add(p);
        } else {
            participantsByCity.put(city, new HashSet<>());
            participantsByCity.get(city).add(p);
        }
    }

    public void listByCity(String city) {
       Set<Participant> sortedSet = new TreeSet<>(Comparator.comparing(Participant::getName)
               .thenComparingInt(Participant::getAge)
               .thenComparing(Participant::getCode));
        sortedSet.addAll(participantsByCity.get(city));

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
                audition.addParticipant(parts[0], parts[1], parts[2],
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