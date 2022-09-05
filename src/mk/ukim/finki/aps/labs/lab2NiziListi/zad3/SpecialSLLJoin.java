package mk.ukim.finki.aps.labs.lab2NiziListi.zad3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class SpecialSLLJoin {

    public static void main(String[] args) throws IOException {

        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        SLL<Integer> lista1 = new SLL<Integer>();
        for (int i = 0; i < N; i++) {
            lista1.insertLast(Integer.parseInt(pomniza[i]));
        }

        s = stdin.readLine();
        N = Integer.parseInt(s);
        s = stdin.readLine();
        pomniza = s.split(" ");
        SLL<Integer> lista2 = new SLL<Integer>();
        for (int i = 0; i < N; i++) {
            lista2.insertLast(Integer.parseInt(pomniza[i]));
        }

        SLL<Integer> spoeni = new SLL<>();

        spoeni = spoeni.specialJoin(lista1, lista2);
        // spoeni = specialJoin(lista1, lsita2);
        // za ova da raboti so private klasa vo ovaa 'SpecialSLLJoin.java'

        Iterator<Integer> iterator = spoeni.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
            if (iterator.hasNext()) {
                System.out.print(" ");
            }
        }

        System.out.println();

    }
}
