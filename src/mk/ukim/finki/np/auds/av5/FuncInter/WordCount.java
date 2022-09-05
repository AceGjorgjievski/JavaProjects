package mk.ukim.finki.np.auds.av5.FuncInter;

import java.io.*;

public class WordCount {

    public static void readData(InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        LineCounter result = bufferedReader.lines()
                .map(LineCounter::new)
                .reduce(
                        new LineCounter(0, 0, 0),
                        LineCounter::sum
                );

        System.out.println(result);
    }


    public static void main(String[] args) {
        File file = new File("C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\np\\finkiNP.txt");
        try {
            readData(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
