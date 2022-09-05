package mk.ukim.finki.np.ispit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Lines {
    private int number;
    private int max;

}

class NumberProcessor {
    private List<String> integerList;

    public NumberProcessor() {
        integerList = new ArrayList<>();
    }

    public void readRows(InputStream is) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(is));

        this.integerList = bf.lines().collect(Collectors.toList());


//        System.out.println(integerList.get(0));
    }

    public int findMaxElement(String line) {
        String [] parts = line.split("\\s++");
        int max = Integer.parseInt(parts[0]);
        for(int i=1; i<parts.length; i++) {
            if(max < Integer.parseInt(parts[i])) {
                max = Integer.parseInt(parts[i]);
            }
        }
        return max;
    }

    //1 1 2 2 1
    public int findIndex(String line) {
        String [] parts = line.split("\\s++");
        int firstNum,innerNum,counter=1,maxCounter=1;
        int ind = 0;
        for(int i=0; i<parts.length-1; i++) {
            firstNum = Integer.parseInt(parts[i]);
            for(int j=i+1; j<parts.length; j++) {
                innerNum = Integer.parseInt(parts[j]);
                if(firstNum == innerNum) {
                    counter++;
                }
            }
            if(maxCounter <= counter) {
                maxCounter = counter;
                ind = i;
                counter = 1;
            } else {
                counter = 1;
            }
        }
        return ind;
    }

    //
    //i
    //   j
    public String findFrequency(String line) {
        String [] parts = line.split("\\s++");
        int maxElement = findMaxElement(line);//2
        int num,currCounter=1,maxCounter=Integer.MIN_VALUE,innerNum;
        int ind = findIndex(line),maxNum=Integer.MIN_VALUE;

        //6 6 7 7 8 8
        //i
        //  j
        for(int i=0; i<parts.length-1; i++) {
            num = Integer.parseInt(parts[i]);
            for(int j=i+1; j<parts.length; j++) {
                innerNum = Integer.parseInt(parts[j]);
                if(num == innerNum && num == maxElement) {
                    currCounter++;
                }
            }
            if (maxCounter < currCounter) {
                maxCounter = currCounter;
            } else {
                currCounter = 1;
            }
        }
        if(Integer.parseInt(parts[ind]) == maxElement)
            return String.valueOf(maxElement);
        else return "";
    }

    public void printMaxFromRows(PrintStream out) {
        PrintWriter printWriter = new PrintWriter(out);

        for(int i=0; i<this.integerList.size(); i++) {
            String max = findFrequency(integerList.get(i));
            if(max.length()>0)
                printWriter.println(max);
        }

        printWriter.flush();
        printWriter.close();


    }
}


public class NumbersProcessorTest {

    public static void main(String[] args) {


        NumberProcessor numberProcessor = new NumberProcessor();

        try {
            numberProcessor.readRows(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        numberProcessor.printMaxFromRows(System.out);


//        System.out.println(numberProcessor.findIndex("1 1 1 1 2 3 88"));


    }
}