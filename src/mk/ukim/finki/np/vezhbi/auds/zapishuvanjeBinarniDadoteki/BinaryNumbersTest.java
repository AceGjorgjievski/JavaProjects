package mk.ukim.finki.np.vezhbi.auds.zapishuvanjeBinarniDadoteki;

import java.io.*;
import java.util.Random;

public class BinaryNumbersTest {
    public static final String FILE_NAME = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\np\\vezhbi\\zapishuvanjeBinarniDadoteki\\result.dat";

    private static void generateFile(int n) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            Random random = new Random();

            for (int i = 0; i < n; i++) {
                int randomInt = random.nextInt(1000);
                objectOutputStream.writeInt(randomInt);
            }
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double average() {
        int count = 0;
        double sum = 0;

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_NAME));

            try {
                while (true) {
                    int number = objectInputStream.readInt();
                    sum += number;
                    count++;
                }
            } catch (EOFException e) {
                System.out.println("End of file was reached");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum / count;
    }

    public static void main(String[] args) {
        generateFile(1000);
        System.out.println("AVG: " + average());
    }


}
