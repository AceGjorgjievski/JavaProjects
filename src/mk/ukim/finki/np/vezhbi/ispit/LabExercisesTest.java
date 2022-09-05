package mk.ukim.finki.np.vezhbi.ispit;

import java.util.*;
import java.util.stream.Collectors;

class Student {
    private String index;
    private List<Integer> points;
    private final static int LAB_EX = 10;
    private final static int CURR_YEAR = 2020;

    public Student() {
        this.points = new ArrayList<>();
        index = "201183";
    }

    public Student(String index, List<Integer> points) {
        this.index = index;
        this.points = points;
    }

    public int getIndex() {
        return Integer.parseInt(index);
    }

    public double totalPoints() {
        int localPoints = this.points.stream()
                .mapToInt(i -> i)
                .sum();
        return localPoints*1.0/LAB_EX;
    }

    public boolean hasSignature(){
        return this.points.size() >= 8;
    }

    public int getYearOfStudies() {
        return CURR_YEAR%100 - Integer.parseInt(this.index.substring(0,2));
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f",
                index,
                hasSignature() ? "YES" : "NO",
                totalPoints());
    }
}

class LabExercises {
    private List<Student> list;
    private Comparator<Student> comparator = Comparator
            .comparing(Student::totalPoints)
            .thenComparingInt(Student::getIndex);
    private Comparator<Student> comparatorFailed = Comparator
            .comparing(Student::getIndex)
            .thenComparing(Student::totalPoints);
    private Comparator<Student> comparatorYear = Comparator
            .comparing(Student::getYearOfStudies);


    public LabExercises() {
        this.list = new ArrayList<>();
    }

    public void addStudent(Student other) {
        this.list.add(other);
    }

    public void printByAveragePoints (boolean ascending, int n) {
        if(!ascending) {
            this.comparator = comparator.reversed();
        }
        this.list.stream()
                .sorted(comparator)
                .limit(n)
                .forEach(System.out::println);
    }

    public List<Student> failedStudents () {
        return this.list.stream()
                .filter(i -> !i.hasSignature())
                .sorted(comparatorFailed)
                .collect(Collectors.toList());
    }
    public Map<Integer,Double> getStatisticsByYear() {
        return this.list.stream()
                .filter(Student::hasSignature)
                .collect(Collectors.groupingBy(Student::getYearOfStudies,Collectors.averagingDouble(Student::totalPoints)));
    }
}

public class LabExercisesTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LabExercises labExercises = new LabExercises();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] parts = input.split("\\s+");
            String index = parts[0];
            List<Integer> points = Arrays.stream(parts).skip(1)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());

            labExercises.addStudent(new Student(index, points));
        }

        System.out.println("===printByAveragePoints (ascending)===");
        labExercises.printByAveragePoints(true, 100);
        System.out.println("===printByAveragePoints (descending)===");
        labExercises.printByAveragePoints(false, 100);
        System.out.println("===failed students===");
        labExercises.failedStudents().forEach(System.out::println);
        System.out.println("===statistics by year");
        labExercises.getStatisticsByYear().entrySet().stream()
                .map(entry -> String.format("%d : %.2f", entry.getKey(), entry.getValue()))
                .forEach(System.out::println);

    }
}