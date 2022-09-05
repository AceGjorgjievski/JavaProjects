package mk.ukim.finki.aps.kol1.vezhbi1kol.bukvi;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    // Stekot e pretstaven na sledniot nacin:
    //depth e dlabochinata na stekot, a
    // elems[0...depth-1] se negovite elementi.
    private E[] elements;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elements = (E[]) new Object[maxDepth];
        depth = 0;
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }

    public E peek() {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            //throw new NoSuchElementException();
            return null;
        return elements[depth - 1];
    }


    public void clear() {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++) elements[i] = null;
        depth = 0;
    }


    public void push(E x) {
        // Go dodava x na vrvot na stekot.
        elements[depth++] = x;
    }

    public E pop() {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            //throw new NoSuchElementException();
            return null;
        /* 1 2 3 4
        E topmost = elems[depth-1];
        elems[depth-1] = null;
        depth--;
        return topmost
         */
        E topmost = elements[--depth];
        elements[depth] = null;
        return topmost;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (E elem : elements) {
            stringBuilder.append(elem).append(" ");
        }
        return stringBuilder.toString();
    }
}

public class Bukvi {

    // A S G T B S T
    // S G T S U O T S P T S T
    public static int conduct (char [] array) {
        Stack<Character> stack = new ArrayStack<>(100);

        for(int i=0; i<array.length; i++) {
            if(array[i] == 'S' || array[i] == 'T') stack.push(array[i]);
        }

        int maxCount = Integer.MIN_VALUE,counter = 0,flag = 1;
        while (!stack.isEmpty()) {
            if(stack.peek() == 'T') {
                counter++;
                stack.pop();
            } else if(stack.peek() == 'S') {
                if(flag == 1) {
                    maxCount = counter;
                    flag = 0;
                } else {
                    if(maxCount != counter) return 0;
                }
                counter = 0;
                stack.pop();
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String line = bf.readLine();

        char [] charArray = line.toCharArray();

        System.out.println(conduct(charArray));
        System.out.println(proveri_t_posle_s(charArray));

    }




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
            else break;

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
                } else return 0;
            }
        }
        return counter == counter2 ? 1 : 0;
    }

}
