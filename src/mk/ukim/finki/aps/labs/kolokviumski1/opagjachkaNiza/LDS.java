package mk.ukim.finki.aps.labs.kolokviumski1.opagjachkaNiza;
import java.util.Scanner;


public class LDS {


    private static int najdolgaOpagackaSekvenca(int[] a) {

        // Vasiot kod tuka

        /*       i
                 k
                     j

                546 -156 165 -156 -56 -13 5
                50  -20  10 -5 6  1
         */
        int counter=1;
        int j,k;
        int maxCounter = 1;

        if(a.length == 1){
            return 1;
        }
        for(int i=0; i<a.length-1; i++){
            j = i+1;
            k = i;
            counter = 1;
            while(j<a.length){
                if(a[k] > a[j]){
                    counter++;
                    k = j;
                }
                j++;
            }
            if(counter > maxCounter){
                maxCounter = counter;
            }
        }
        return maxCounter;

    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}
