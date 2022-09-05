package mk.ukim.finki.np.auds.av5.grades;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Course {
    private List<Student> studentList;

    public Course() {
        this.studentList = new ArrayList<>();
    }


    public void readData(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        this.studentList = bufferedReader.lines()
                .map(l -> Student.createStudent(l))
                .collect(Collectors.toList());
    }

    public void printSortedData(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        this.studentList.stream()
                .sorted()
                .forEach(s -> printWriter.println(s));
        printWriter.flush();

    }

    public void printDetailedData(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);

        this.studentList
                .forEach(s -> printWriter.println(s.detailPrint()));

        printWriter.flush();

    }

    public void printGradeDistribution(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);

        int[] finalGrade = new int[6];

        for (Student s : studentList) {
            finalGrade[s.getGrade() - 'A']++;
        }

        IntStream.range(0,6)
                .forEach(i -> {
                    printWriter.printf("%c -> %d\n", i + 'A', finalGrade[i]);
                });

//        for (int i = 0; i < 6; i++) {
//            printWriter.printf("%c -> %d\n", i + 'A', finalGrade[i]);
//        }
        printWriter.flush();

    }
}