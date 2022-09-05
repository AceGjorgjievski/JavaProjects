package mk.ukim.finki.os.labs.lab_1.task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

interface IManager {
    void filterDocuments(String filePath) throws FileNotFoundException;
}

public class Task1Test implements IManager{

    @Override
    public void filterDocuments(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        if(!f.exists()) throw new FileNotFoundException();
        if(!f.isDirectory()) throw new FileNotFoundException();

        File [] files = f.listFiles();

        Arrays.stream(files)
                .filter(i -> i.length() > 50)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        String source = "";
        Task1Test lab1Test = new Task1Test();
        try {
            lab1Test.filterDocuments(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
