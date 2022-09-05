package mk.ukim.finki.np.auds.av8;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class CountOccurencesTest {

    public static int count(Collection<Collection<String>> c, String str){
//        int counter = 0;

//        for(Collection<String> collection : c){
//            for(String element : collection){
//                if(element.equalsIgnoreCase(str)){
//                    counter++;
//                }
//            }
//        }

        return (int) c.stream()
                .flatMap(i -> i.stream())
                .filter(i -> i.equalsIgnoreCase(str))
                .count();

//        return counter;
    }

    public static void main(String[] args) {
        List<String> list = List.of("1","2","3","4","5");
        List<String> list2 = List.of("2", "4", "5", "7" ,"8");

        String line = new String(String.valueOf(new Scanner(System.in)));

       // System.out.println(count(,line));
    }
}
