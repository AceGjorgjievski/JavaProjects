package mk.ukim.finki.aps.labs.lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class OddEvenSort {

    /*
   		6 10 13 5 8 17 2 5

        13 5 17 5 // 6 10 8 2

        5 5 13 17 // 10 8 6 2

        (a) -> 2 5 5 6 8 10 13 17
        (tempP) -> 2 6 8 10
        (tempN) -> 5 5 13 17
        ==============

        5 5 13 17 10 8 6 2


       1. sortiraj ja celata niza;
       2. napravi dve temp nizi za da se smestat parnite vo edna i neparnite vo druga (tempP i tempN)
       3. soodvetno ako e %2 == (paren) izbroj gi koklku elementi ima(numOfEven) isto i za numOfOdds(%2 != 0)
       4. bidejkji vo tempN e sortirani spored rastechki redosled, sega treba da se smeni tempP vo opagjachki
       (taka se bara vo zadachata)
       5. se prakja vo bubble sort tempP za da se sortira vo opagjachki redosled(se vika bubbleSortDescending
       bidejkji kaj uslovot se koristi '<', kje beshe ascending ako se koristeshe '>', da nema zbunka)
    */


    static void bubbleSortDescending(int[] a, int n) {
        int i, j, temp = 0;
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - 1 - i; j++){
                if(a[j] < a[j+1]){
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }


    static void oddEvenSort(int[] a, int n) {
        // Vasiot kod tuka

        Arrays.sort(a);
        // 2 5 5 6 8 10 13 17
        int numOfEven=0,numOfOdds=0;
        for(int i=0; i<n; i++){
            if(a[i] % 2 == 0){
                numOfEven++;
            } else {
                numOfOdds++;
            }
        }

        int[] tempP = new int[numOfEven];
        int[] tempN = new int[numOfOdds];

        int j = 0, k = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                tempP[j++] = a[i];
            } else {
                tempN[k++] = a[i];
            }
        }

        for(int i=0; i< tempP.length; i++){
            System.out.print(tempP[i] + " ");
        }
        System.out.println();

        for (int i=0; i< tempN.length; i++){
            System.out.print(tempN[i] + " ");
        }
        System.out.println();

        //tempP = 2 6 8 10
        //tempN = 5 5 13 17



        bubbleSortDescending(tempP, tempP.length);

        int m = 0;
        for(int i=0; i< numOfOdds; i++){
            a[i] = tempN[i];
            m++;
        }

        for(int i=0; i< numOfEven; i++){
            a[m++] = tempP[i];
        }



//        int j=0;
//        Arrays.stream(a).sorted()
//                .forEach(i -> {
//                    if(i%2 == 0)
//                        tempP[j++] = i;
//                });
//
//        Arrays.stream(tempP)
//                .forEach(i -> System.out.print(i + " "));
//
//        System.out.println();


    }

    public static void main(String[] args) throws IOException {
        int i;
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String[] pom = s.split(" ");
        int[] a = new int[n];
        for (i = 0; i < n; i++)
            a[i] = Integer.parseInt(pom[i]);
        oddEvenSort(a, n);
        for (i = 0; i < n - 1; i++)
            System.out.print(a[i] + " ");
        System.out.print(a[i]);
    }
}
