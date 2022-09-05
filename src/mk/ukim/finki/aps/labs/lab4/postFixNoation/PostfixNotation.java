package mk.ukim.finki.aps.labs.lab4.postFixNoation;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

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
    }

    public E pop() {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (top == null)
            throw new NoSuchElementException();
        counter--;

        E topElem = top.element;
        top = top.succ;
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


public class PostfixNotation {

    private static int checkValidPostNotation(char [] line) {
        //60 80 * 20 40 * /

        ArrayStack<Integer> arrayStack = new ArrayStack<>(100);

        int number = 0;
        for(int i=0; i<line.length; i++){
            char curr = line[i];
            if(Character.isDigit(curr) && Character.isDigit(line[i+1])) {
                number = number*10 + Character.getNumericValue(curr);
            } else if(Character.isDigit(curr) && !Character.isDigit(line[i+1])){
                number = number *10 + Character.getNumericValue(curr);
                arrayStack.push(number);
                number = 0;
            } else if(curr == '+' || curr == '-' || curr == '*' || curr == '/'){
                    int num1 = arrayStack.pop();
                    int num2 = arrayStack.pop();
                    if(curr == '+') {
                        arrayStack.push(num1 + num2);
                    } else if(curr == '-') {
                        arrayStack.push(num2 - num1);
                    } else if(curr == '*') {
                        arrayStack.push(num1 * num2);
                    } else if(curr == '/') {
                        arrayStack.push(num2 / num1);
                    }
                }
            }
        return arrayStack.pop();
    }




    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = null;

        try {
            line = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        char [] chars = line.toCharArray();

        System.out.println(checkValidPostNotation(chars));


    }


}

