package mk.ukim.finki.vezhbi.algoritmi.vezhb2.techAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;


public class Test2listi {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        SLL<Integer> lista1 = new SLL<>();
        SLL<Integer> lista2 = new SLL<>();

        for(int i=0; i<N; i++) {
            int temp = Integer.parseInt(bf.readLine());
            lista1.insertLast(temp);
        }

        int M = Integer.parseInt(bf.readLine());

        for(int i=0; i<M; i++) {
            int temp = Integer.parseInt(bf.readLine());
            lista2.insertLast(temp);
        }

        SLL<Integer> finalList = lista1.listiNaizmenichno(lista2);

        Iterator<Integer> finalIterator = finalList.iterator();

        while (finalIterator.hasNext()) {
            System.out.print(finalIterator.next() + " ");
        }

    }
}
