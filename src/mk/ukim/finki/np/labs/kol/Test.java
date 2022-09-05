package mk.ukim.finki.np.labs.kol;

import java.util.ArrayList;
import java.util.List;

public class Test {

    private static List<String> subNumbers(String number) {
        //077 123
        List<String> temp = new ArrayList<>();
        for(int i=3; i<=number.length(); i++) {
            for(int j=0; j<=number.length()-i; j++) {
                temp.add(number.substring(j,i+j));
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(subNumbers("075376026"));
    }
}
