package mk.ukim.finki.aps.labs.lab3.zigzag;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {

    static int najdiNajdolgaCikCak(int a[]) {
        // Vasiot kod tuka
        int counter = 1,k;
        int maxLen = 1;

        for(int i=0; i<a.length-1; i++){
            if(a.length == 1) return 1;

            if(a[i] == 0 || a[i+1] == 0){
                if(counter > maxLen){
                    maxLen = counter;
                }
                counter = 1;
                i++;
            } // 1 -1 0 1

            if(a[i] > 0 && a[i+1] > 0){
                if(counter > maxLen){
                    maxLen = counter;
                }
                counter = 1;// -1 1 -1 -1 2 -4 2
                i++;
            } else if(a[i] < 0 && a[i+1] < 0){
                if(counter > maxLen){
                    maxLen = counter;
                }
                counter = 1;
                i++;
            }

            if(a[i] > 0 && a[i+1] < 0){
                counter++;
            } else if(a[i] < 0 && a[i+1] > 0){
                counter++;
            }
        }

        if(counter > maxLen){
            return counter;
        }
        return maxLen;
    }

    /*
                k
        -1  4  -3  2

     */

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }

}