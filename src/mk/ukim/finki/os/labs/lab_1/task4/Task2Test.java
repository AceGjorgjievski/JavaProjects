package mk.ukim.finki.os.labs.lab_1.task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

interface IManager {

    void executeRec(String path) throws FileNotFoundException;
}

public class Task2Test implements IManager{


    @Override
    public void executeRec(String path) throws FileNotFoundException {
        File file = new File(path);
        if(!file.exists()) throw new FileNotFoundException();
        if(!file.isDirectory()) throw new FileNotFoundException();
        File [] files = file.listFiles();

        for(File f : files) {
            if(f.isDirectory()) {
                executeRec(f.getAbsolutePath());
            } else {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        String source = "";
        Task2Test task2Test = new Task2Test();
        try {
            task2Test.executeRec(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
