package mk.ukim.finki.np.vezhbi.labs.kontaktiodlabs;

import java.util.Arrays;
import java.util.Comparator;

public class Faculty {
    private Student[] students;
    private String name;

    public Faculty(String name, Student[] students) {
        this.name = name;
        this.students = students;
    }

    public int countStudentsFromCity(String cityName) {
        int counter = 0;
        for (Student student : students) {
            if (student.getCity().equals(cityName)) {
                counter++;
            }
        }
        return counter;
    }

    public Student getStudent(long index) {
        for (Student student : students) {
            if (student.getIndex() == index) {
                return student;
            }
        }
        return null;
    }

    public double getAverageNumberOfContacts() {
        double sum = 0.0;

        for (Student student : students) {
            sum += student.getNumberOfContacts();
        }

        return (sum / students.length);
    }

    public Student getStudentWithMostContacts() {
//        Student maxStudent = students[0];
//        long maxIndex = maxStudent.getIndex();
//        int maxIndexLoop = -1;
//
//        Student[] temp = new Student[students.length];
//
//        for (int i = 1; i < students.length; i++) {
//            if (maxStudent.getNumberOfContacts() < students[i].getNumberOfContacts()) {
//                maxStudent = students[i];
//                maxIndexLoop = i;
//            }
//        }
//
//        int j = 0;
//        for (int i = 0; i < students.length; i++) {
//            if (maxStudent.getNumberOfContacts() == students[i].getNumberOfContacts()) {
//                temp[j++] = students[i];
//            }
//        }
//
//        Student finalMaxStudent = null;
//        for (int i=0; i<temp.length; i++) {
//            if(maxIndex < temp[i].getIndex()) {
//                finalMaxStudent = temp[i];
//                maxIndex = temp[i].getIndex();
//            }
//        }
//
//        return finalMaxStudent;
        return Arrays.stream(students)
                .max(Comparator.comparing(Student::getNumberOfContacts)
                        .thenComparing(Student::getIndex))
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Faculty{" + ", name='" + name + '\'' +
                "students=" + Arrays.toString(students) +
                '}';
    }
}
