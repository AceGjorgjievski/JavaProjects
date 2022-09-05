package mk.ukim.finki.np.auds.av5.grades;

import java.io.*;

public class StudentGrades {
    public static void main(String[] args) {

        Course course = new Course();
        File inputFile = new File("C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\np\\txt\\finkiNP3.txt");
        File outputFile = new File("C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\np\\txt\\finkiNP3.1.txt");

        try {
            course.readData(new FileInputStream(inputFile));
            System.out.println("===Printing sorted students to screen===");
            course.printSortedData(System.out);

            System.out.println("===Printing detailed report to file===");
            course.printDetailedData(new FileOutputStream(outputFile));

            System.out.println("===Printing grade distribution to screen===");
            course.printGradeDistribution(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
