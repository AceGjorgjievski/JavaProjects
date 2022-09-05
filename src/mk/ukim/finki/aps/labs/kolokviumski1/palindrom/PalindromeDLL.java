package mk.ukim.finki.aps.labs.kolokviumski1.palindrom;

import java.util.Scanner;

public class PalindromeDLL {

    public static int isItPalindrome(DLL<Integer> list){
        //Vashiot kod tuka...
        int flag = 1;
        DLLNode<Integer> first = list.getFirst(), last = list.getLast();

        while((first != null) && (last != null)){
            if(first.element.compareTo(last.element) != 0){
                flag = -1;
                return flag;
            }
            first = first.succ;
            last = last.pred;
        }
        return flag;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        DLL<Integer> list = new DLL<Integer>();
        for (int i = 0; i < n; i++) {
            list.insertLast(in.nextInt());
        }
        in.close();
        System.out.println(isItPalindrome(list));
    }

}
