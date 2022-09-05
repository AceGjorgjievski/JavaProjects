package mk.ukim.finki.os.labs.lab_1.task6;


import java.io.*;

interface IManager {
    void execute(String source,String destination) throws IOException;
}

public class Task6Test implements IManager{


    @Override
    public void execute(String source, String destination) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(source)));
            bw = new BufferedWriter(new FileWriter(destination));
            String line = null;

            while((line = br.readLine()) != null) {
                if(Character.isDigit(line.charAt(0))) {
                    bw.write(line + "\n");
                    bw.flush();
                }
            }

        } catch (IOException e) {
            System.out.println("IOException - execute");
        } finally {
            if(br != null) br.close();
            if(bw != null) bw.close();
        }

    }

    public static void main(String[] args) {
        String source = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\os\\labs\\lab_1\\task6\\source.txt";
        String destination = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\os\\labs\\lab_1\\task6\\destination.txt";

        Task6Test task6Test = new Task6Test();
        try {
            task6Test.execute(source,destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
