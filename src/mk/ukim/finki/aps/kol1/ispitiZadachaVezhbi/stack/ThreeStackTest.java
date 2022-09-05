package mk.ukim.finki.aps.kol1.ispitiZadachaVezhbi.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

//Three bidejkji 3 steka se, myb zatoa taka sum ja krstil

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

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


class LinkedStack<E> implements Stack<E> {

    //Stekot e pretstaven na sledniot nacin: top e link do prviot jazol
    // na ednostrano-povrzanata lista koja sodrzi gi elementite na stekot .
    private SLLNode<E> top;
    private int counter = 0;
    private int sum = 0;

    public LinkedStack() {
        // Konstrukcija na nov, prazen stek.
        top = null;
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako stekot e prazen.
        return (top == null);
    }

    public int length() {
        return counter;
    }

    public int getSum() {
        return sum;
    }

    public E peek() {
        // Go vrakja elementot na vrvot od stekot.
        if (top == null)
            throw new NoSuchElementException();
        //or return null;
        return top.element;
    }

    public void clear() {
        // Go prazni stekot.
        top = null;
    }

    public void push(E x) {
        // Go dodava x na vrvot na stekot.

        /*
        SLLNode<E> ins = new SLLNode<>(x,top);
          top = ins;
         */
        top = new SLLNode<E>(x, top);
        counter++;
        sum +=(int)x;

    }

    public E pop() {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (top == null)
            throw new NoSuchElementException();
        counter--;


        E topElem = top.element;
        top = top.succ;
        sum -= (int) topElem;
        return topElem;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        while (top != null) {
            sb.append(top).append(" ");
            top = top.succ;
        }
        return sb.toString();
    }


    public String toString2() {
        StringBuilder sb = new StringBuilder();
        while (top != null) {
            if (top.succ == null) {
                sb.append(top);
                break;
            } else {
                sb.append(top).append("->");
            }
            top = top.succ;
        }
        return sb.toString();
    }
}

public class ThreeStackTest {

    /*
5 3 4
3 2 1 1 1
4 3 2
1 1 4 1
     */


    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String line = bf.readLine();
        String [] parts = line.split("\\s++");

        int N = Integer.parseInt(parts[0]);
        int M = Integer.parseInt(parts[1]);
        int K = Integer.parseInt(parts[2]);

        int [] array1 = new int[N];
        int [] array2 = new int[M];
        int [] array3 = new int[K];

        line = bf.readLine();
        String [] lines1 = line.split("\\s++");
        for(int i=0; i<array1.length; i++) {
            array1[i] = Integer.parseInt(lines1[i]);
        }

        line = bf.readLine();
        String [] lines2 = line.split("\\s++");
        for(int i=0; i<array2.length; i++) {
            array2[i] = Integer.parseInt(lines2[i]);
        }

        line = bf.readLine();
        String [] lines3 = line.split("\\s++");
        for(int i=0; i<array3.length; i++) {
            array3[i] = Integer.parseInt(lines3[i]);
        }

        LinkedStack<Integer> stack1 = new LinkedStack<>();
        LinkedStack<Integer> stack2 = new LinkedStack<>();
        LinkedStack<Integer> stack3 = new LinkedStack<>();


        for(int i=array1.length-1; i>=0; i--) {
            stack1.push(array1[i]);
        }
        for(int i=array2.length-1; i>=0; i--) {
            stack2.push(array2[i]);
        }
        for(int i=array3.length-1; i>=0; i--) {
            stack3.push(array3[i]);
        }



        //to do ->implement the sum
        if(stack1.getSum() == stack2.getSum() && stack2.getSum() == stack3.getSum()) {
            System.out.println(stack1.getSum());
            return;
        }

        while (true) {
            if(stack1.getSum() == stack2.getSum() && stack2.getSum() == stack3.getSum()) {
                System.out.println(stack1.getSum());
                break;
            } else if(stack1.getSum() == stack2.getSum()) {
                if(stack2.getSum() < stack3.getSum()) {
                    stack3.pop();
                } else {
                    stack1.pop();
                    stack2.pop();
                }
            } else if(stack2.getSum() == stack3.getSum()) {
                if(stack2.getSum() < stack1.getSum()) {
                    stack1.pop();
                } else {
                    stack2.pop();
                    stack3.pop();
                }
            } else if(stack1.getSum() == stack3.getSum()) {
                if(stack1.getSum() < stack2.getSum()) {
                    stack2.pop();
                } else {
                    stack1.pop();
                    stack3.pop();
                }
            } else {
                int max = findMax(stack1.getSum(), stack2.getSum(), stack3.getSum());
                if(max == stack1.getSum()) {
                    stack1.pop();
                } else if(max == stack2.getSum()) {
                    stack2.pop();
                } else {
                    stack3.pop();
                }
            }
        }
    }

    public static int findMax(int a, int b, int c) {
        int max = a;
        if(b > max) {
            max = b;
        }
        if(c > max) {
            max = c;
        }

        return max;
    }

}
