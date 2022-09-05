package mk.ukim.finki.vezhbi.algoritmi.vezhb2.stackEx.zagradi;

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

public class Zagradi {

    public static boolean checkBrackets(char [] input) {
        Stack<Character> stack = new ArrayStack<>(100);
        //s  (s – a)  (s – b  (s – c)

        for(int i=0; i<input.length; i++) {
            char currentChar = input[i];
            if(currentChar == '(' ||currentChar == '[' ||currentChar == '{') {
                stack.push(currentChar);
            }
            if(currentChar == ')' ||currentChar == ']' ||currentChar == '}') {
                if(stack.isEmpty()) return false;

                char bracketFromStack = stack.pop();
                if(!matchingBrackets(bracketFromStack)) return false;
            }
        }
        return (stack.isEmpty());
    }

    public static boolean matchingBrackets(char leftBracket) {
        return switch (leftBracket) {
            case '(', '[', '{' -> true;
            default -> false;
        };
    }


    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();

        char [] charArray = line.toCharArray();


        System.out.println(checkBrackets(charArray));

    }
}
