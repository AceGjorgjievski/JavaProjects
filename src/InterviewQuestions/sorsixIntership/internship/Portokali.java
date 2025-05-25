package InterviewQuestions.sorsixIntership.internship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/*
1,2,3,4,5,6,7,8,9,10,11,12,13,14,15
3
3,5
 */

public class Portokali {
    public static List<String> readInput() {
        final Scanner scan = new Scanner(System.in);
        final List<String> items = new ArrayList<>();
        while(scan.hasNextLine()){
            items.add(scan.nextLine());
        }
        return items;
    }

    public static void processInput(List<String> lines) {
        List<Integer> integers = new ArrayList<>();

        String [] parts = lines.get(0).split(",");
        for(String s : parts) {
            integers.add(Integer.parseInt(s));
        }



        int randomNumber = Integer.parseInt(lines.get(1));
        List<Integer> range = new ArrayList<>();

        String [] parts2 = lines.get(2).split(",");
        for(String s : parts2) {
            range.add(Integer.parseInt(s));
        }

//        System.out.println(integers);
//        System.out.println(randomNumber);
//        System.out.println(range);

        int start = range.get(0);
        int end = range.get(1);

        solve(integers, randomNumber, start,end);
    }

    public static void solve(List<Integer> integers, int inputNum, int start, int end) {
        List<Integer> list = new ArrayList<>();
        List<Integer> remainng = new ArrayList<>();
        for(Integer num : integers) {
            if(num%inputNum == 0) {
                list.add(num);
            } else {
                remainng.add(num);
            }
        }
        System.out.println("Цреши: " + list);
        System.out.println("Портокали: " + remainng);

        List<Integer> mature = new ArrayList<>();

        for(Integer num : remainng) {
            if(num >= start && num <= end) {
                mature.add(num);
            }
        }

        System.out.println("Зрели портокали: " + mature);

    }
    public static void orange(List<Integer> integers, int inputNum) {
    }


        public static void main(String[] args) {
        final List<String> lines = readInput();
        processInput(lines);
    }
}