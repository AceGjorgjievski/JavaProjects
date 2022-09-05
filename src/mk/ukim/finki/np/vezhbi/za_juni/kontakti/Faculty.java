package mk.ukim.finki.np.vezhbi.za_juni.kontakti;

import java.util.Arrays;
import java.util.Comparator;

public class Faculty {
    private String name;
    private Student [] students;

    public Faculty(String name, Student[] students) {
        this.name = name;
        this.students = students;
    }

    public int countStudentsFromCity(String cityName) {
        return (int)Arrays.stream(this.students)
                .filter(i -> i.getCity().equals(cityName))
                .count();
    }

    public double getAverageNumberOfContacts() {
        return Arrays.stream(this.students)
                .mapToInt(Student::getNumberOfContacts).sum()/(this.students.length*1.0);
    }

    public Student getStudentWithMostContacts() {
        return Arrays.stream(this.students)
                .max(Comparator.comparing(Student::getNumberOfContacts).thenComparing(Student::getIndex))
                .orElse(null);
    }

    @Override
    public String toString() {
        String studentsString = students.toString();
        return String.format("{\"fakultet\":\"%s\", \"studenti\":%s}", name, studentsString);
    }

    public Student getStudent(long index) {
        return Arrays.stream(this.students)
                .filter(i -> i.getIndex() == index)
                .findFirst()
                .orElse(null);

    }
}
