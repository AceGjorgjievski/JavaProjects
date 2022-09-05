package mk.ukim.finki.aps.labs.lab2NiziListi.zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Array<E> {

    public static int sum(int [] niza){
        int suma = 0;
        for(int i=0; i<niza.length; i++){
            suma+=niza[i];
        }
        return suma;
    }

    public static int getLength(int [] niza){
        int num = 0;
        for(int i=0; i<niza.length; i++){
            num++;
        }
        return num;
    }

    public static boolean daliPostoi(double num, int [] niza){
        for(int i=0; i<niza.length; i++){
            if(niza[i] == num)
                return true;
        }
        return false;
    }
    /*
    *


     1 2 2 5 7 1 9

    1 2 3 4 5 6
    21/6 = 3.5

    for(int i=0,int j=n-1; i<n; i++,j--){
        if(a[i] < broj && a[j] > broj)


    * */
    public static int brojDoProsek(int [] niza){
        double srednaVrednost = sum(niza)/getLength(niza);
        if(daliPostoi(srednaVrednost,niza))
            return (int) srednaVrednost;
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        int[] niza = new int[N];

        Scanner scanner = new Scanner(System.in);

        for(int i=0; i<N; i++){
            niza[i] = scanner.nextInt();
        }
       System.out.println(brojDoProsek(niza));
    }

}
