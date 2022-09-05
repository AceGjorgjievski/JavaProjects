package mk.ukim.finki.aps.labs.kolokviumski1.StackBukvi;

import java.io.IOException;
import java.util.Scanner;

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            //throw new NoSuchElementException();
            return null;
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            //throw new NoSuchElementException();
            return null;
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class StackBukvi {

    /*
        STUSTHSTST
     */

    static int proveri_t_posle_s(char [] expression)
    {
        Stack<Character> stack = new ArrayStack<>(expression.length);

        int n = 0;
        for(int i=0; i<expression.length; i++){
            if(expression[i] == 'S' || expression[i] == 'T'){
                stack.push(expression[i]);
                n++;
            }
        }
        // STTTSTSTTTSTTT
        int counter = 0;
        for(int i=0; i<n; i++){
            char curr = stack.pop();
            if(curr == 'T') counter++;
            else {
                break;
            }
        }
        // STUSTHSTST
        //STST
        int counter2 = 0;
        for(int i=0; i<(n-counter-1); i++){
            char curr = stack.pop();
            if(curr == 'T') counter2++;
            else {
                if(counter == counter2){
                    if(i+1 < (n-counter-1)){
                        counter2 = 0;
                    }
                } else {
                    return 0;
                }
            };
        }

        return counter == counter2 ? 1 : 0;
    }

    public static void main(String[] args) throws IOException {
        char [] niza=new char[100];

        Scanner f=new Scanner(System.in);
        String st=f.next();
        niza=st.toCharArray();

        int rez= proveri_t_posle_s(niza);
        System.out.println(rez);
    }


}