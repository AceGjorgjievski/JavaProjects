package mk.ukim.finki.np.kol;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
BufferedReader bf = new BufferedReader(new InputStreamReader(in));
        List<String> stringList = new ArrayList<>();

        //zadaca
        stringList = bf.lines()
                .filter(i -> (
                        IntStream.range(0, i.length())
                                .anyMatch(j -> Character.toLowerCase(j) == a)
                )).max()

        PrintWriter printWriter = new PrintWriter(out);

        printWriter.println(stringList.toString());
        printWriter.flush();;
 */

/*
Да се имплементира класата LineProcessor со единствен метод:

void readLines (InputStream is, OutputStream os, char c) којшто од влезен поток ќе ги прочита сите стрингови
 (секој од нив во нов ред), а на излезен поток ќе ја испечати линијата/стрингот којшто го содржи карактерот
  c најмногу пати. Доколку има повеќе такви линии да се испечати последната. Да се игнорира големината на буквите
Задачата мора да ги помине сите тест примери за да се продолжи на главниот дел од испитот.

Input	                                    Result

FINKI
Napredno programiranje
Naaaaapredno programiraaaanje
Javaaa

Naaaaapredno programiraaaanje
Naaapredno
programiraanje
zadaca
                                                zadaca

 */

//class Lines {
//    private int max;
//    public Lines() {
//    }
//
//    public static int countOccurences(String line,char c){
//        int counter = 0;
//        for(int i=0; i<line.length(); i++){
//            if(line.charAt(i) == Character.toLowerCase(c)){
//                counter++;
//            }
//        }
//        return counter;
//    }
//}

class LineProcessor {
//    List<Lines> list;



    public int countOccurencesLocal(String line, char c) {
        int counter = 0;
        for (int i = 0; i < line.length(); i++) {
            if (Character.toLowerCase(line.charAt(i)) == c) counter++;
        }
        return counter;
    }

    public static boolean hasCharacter(String line, char c) {
        return Arrays.stream(line.chars().toArray())
                .anyMatch(i -> Character.toLowerCase(i) == c);
    }

    public void readLines(InputStream in, PrintStream out, char a) throws IOException {


        BufferedReader bf = new BufferedReader(new InputStreamReader(in));

        String letter = String.valueOf(a);

        List<String> stringList =
                bf.lines()
                        .filter(i -> i.contains(letter))
                        .collect(Collectors.toList());

        //od ova posle count? na

        if(stringList.isEmpty()){
            System.out.println("prazno");//ne znam dali prazno ili mislam 1ta rechenica da se vrati
        } else {
            stringList
                    .forEach(System.out::println);
        }

//
        /*
FINKI
Napredno programiranje
zadaca
Naaaaapredno programiraaaanje
Javaaa
zadaccca
        */
//
//
//        while ((currLine = bf.readLine()) != null) {
//            if(hasCharacter(currLine.trim(),a)) {
//                countC = countOccurencesLocal(currLine.trim(),a);
//                flag = 1;
//                if(maxC <= countC) {
//                    maxC = countC;
//                    finalLine = currLine;//zadaca
//                }
//            } else {
//                currLen = currLine.length();
//                if(maxLength < currLen){
//                    maxLength = currLen;
//                    maxLine = currLine;
//                }
//            }
//        }
//
//        PrintWriter printWriter = new PrintWriter(out);
//        if(flag == 1) {
//            printWriter.println(finalLine);
//        } else {
//            printWriter.println(maxLine);
//        }
//
//        printWriter.flush();
    }
}


 /*   public void readLines(InputStream in, PrintStream out, char a) throws IOException {
        Scanner scanner = new Scanner(System.in);
       // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));


//        String max= bufferedReader.readLine();
        String max = scanner.nextLine();

        int temp = countOccurencesLocal(max,a);
               // System.out.println(max + " " + temp);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.equals(" ")) break;
            int temp2 = countOccurencesLocal(line,a);
            if(temp2 >= temp){
                max = line;
            }
        }

        System.out.println(max);

//        while(bufferedReader.readLine() != null){
//            String tempString = bufferedReader.readLine();
//            if(tempString.equals(" ")) break;
//            int temp2 = countOccurencesLocal(tempString,a);
//            if(temp2 >= temp){
//                max = tempString;
//            }
//        }

//        if(max != null){
//          //  bufferedReader.close();
//            PrintWriter printWriter = new PrintWriter(out);
//            printWriter.println(max);
//            printWriter.flush();
//        } else {
//            ;throw  new IOException();
//        }

//        list = new ArrayList<>();
//
//        // Napredno programiranjec
//        // Naaaaapredno programiraaaanje
//        // abvc
//        //
//        list.stream()
//                .mapToInt(i-> Lines.countOccurences(i,a))
//                .max()
//                .


//        list = bufferedReader.lines()
//                .mapToInt(i -> Lines.countOccurences(i,a))
//                .max().stream().collect(Collectors.toList());
//
//        int j=0, flag = 1;
//        String max = scanner.nextLine();
//        int temp = Lines.countOccurences(max,a);
//        while(scanner.hasNextLine()) {
//            if(!scanner.hasNext()) throw new IOException();
//            String line = scanner.nextLine();
//            int temp2 = Lines.countOccurences(line,a);
//            if(temp2 >= temp){
//                max = line;
//            }
//        }
//        PrintWriter printWriter = new PrintWriter(out);
//        printWriter.println(String.format("%s",max));
//        printWriter.flush();

    }
}*/

public class LineProcessorTest {
    public static void main(String[] args) {
        LineProcessor lineProcessor = new LineProcessor();

        try {
            lineProcessor.readLines(System.in, System.out, 'c');
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


