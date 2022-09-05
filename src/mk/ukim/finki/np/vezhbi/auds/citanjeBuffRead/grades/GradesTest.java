package mk.ukim.finki.np.vezhbi.auds.citanjeBuffRead.grades;

import java.io.*;

public class GradesTest {


    public static void main(String[] args) throws FileNotFoundException {
        Course course = new Course();
        File inputFile = new File("C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\np\\vezhbi\\citanjeBuffRead\\grades\\given");
        File outputFile = new File("C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\np\\vezhbi\\citanjeBuffRead\\grades\\results");

        course.readData(new FileInputStream(inputFile));
        System.out.println("==Printing sorted students to screen==");
        course.printSortedData(System.out);

        System.out.println("==Printing detailed report to file==");
        course.printDetailedData(new FileOutputStream(outputFile));

        System.out.println("==Printing grade distribution on screen==");
        course.printDistribution(System.out);

    }
}
