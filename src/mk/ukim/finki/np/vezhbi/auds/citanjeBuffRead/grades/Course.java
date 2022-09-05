package mk.ukim.finki.np.vezhbi.auds.citanjeBuffRead.grades;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Course {
    private List<Student> students;

    public Course() {
        students = new ArrayList<>();
    }

    public void readData(InputStream inputStream) {
       BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
       this.students = bf.lines()
               .map(i -> Student.createStudent(i))
               .collect(Collectors.toList());
    }
    public void printSortedData(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);

        this.students.stream()
                .sorted()
                .forEach(i -> printWriter.println(i));

        printWriter.flush();
    }
    public void printDetailedData(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);

        this.students.stream()
                .forEach(i ->printWriter.println(i.printFullInformation()));

        printWriter.flush();

    }
    public void printDistribution(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);

        int [] grades = new int[6];
        for(Student s : students) {
            grades[s.getGrade() - 'A']++;
        }
        for (int i=0; i<6; i++)
            printWriter.printf("%c --> %d\n",i+'A',grades[i]);
        printWriter.flush();
    }
}
