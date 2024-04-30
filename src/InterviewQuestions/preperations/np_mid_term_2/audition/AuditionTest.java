package InterviewQuestions.preperations.np_mid_term_2.audition;

import java.util.*;

class Participant {
    private String city;
    private String code;
    private String name;
    private int age;

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
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", this.code, this.name, this.age);
    }
}

class Audition {
    private Map<String, Set<Participant>> participants;

    public Audition() {
        this.participants = new HashMap<>();
    }

    public void addParticipant(String city, String code, String name, int age) {
        Participant newParticipant = new Participant(city,code, name, age);
        if(!this.participants.containsKey(city)) {
            this.participants.put(city,new HashSet<>());
        }
        this.participants.get(city).add(newParticipant);

    }

    public void listByCity(String city) {
        Set<Participant> participants = this.participants.get(city);
        Set<Participant> sortedSet = new TreeSet<>(
                Comparator.comparing(Participant::getName)
                        .thenComparing(Participant::getAge)
                        .thenComparing(Participant::getCode)
        );
        sortedSet.addAll(participants);

        sortedSet.forEach(System.out::println);

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
