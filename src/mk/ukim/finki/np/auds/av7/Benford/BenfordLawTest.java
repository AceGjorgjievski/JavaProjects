package mk.ukim.finki.np.auds.av7.Benford;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class CountNumbers {
    int [] arrayNum;
    int digit;

    public CountNumbers(int num) {
        this.arrayNum = new int[num];
        digit = 0;
    }

    public void digitCount(int num){
        arrayNum[num]++;
        digit++;
    }

    public int reduceNum(int num){
        while (num > 9){
            num /=10;
        }
        return num;
    }
    /*
    1
    4
    3
    2
    1
    6
    2
    1

     */

    @Override
    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        for(int i=1; i<10; i++){
//            stringBuilder.append(String.format("%d -> %.2f\n",i,1.0*arrayNum[i]/digit * 100.0));
//        }
//        return stringBuilder.toString();

        return IntStream.range(1,10)
                .mapToObj(i -> String.format("%d -> %.2f",i,1.0*arrayNum[i]/digit * 100.0))
                .collect(Collectors.joining("\n"));
    }
}

class BenfordLaw {
    List<Integer> list;
    CountNumbers count;

    BenfordLaw(){
        list = new ArrayList<>();
        count = new CountNumbers(10);
    }

    public void readData(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        list = bufferedReader.lines()
                .filter(i -> i.length() > 0)
                .map(i -> Integer.parseInt(i))
                .collect(Collectors.toList());
    }

    public void conduct(){
        list.stream()
                .map(i -> count.reduceNum(i))
                .forEach(i -> count.digitCount(i));
    }

    @Override
    public String toString() {
        return count.toString();
    }
}

public class BenfordLawTest {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\np\\auds\\av7\\Benford\\librarybooks.txt");
        BenfordLaw benfordLaw = new BenfordLaw();
        try {
            benfordLaw.readData(new FileInputStream(file));
            benfordLaw.conduct();
            System.out.println(benfordLaw);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
