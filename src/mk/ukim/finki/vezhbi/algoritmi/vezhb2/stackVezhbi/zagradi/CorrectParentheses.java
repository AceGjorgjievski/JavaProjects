package mk.ukim.finki.vezhbi.algoritmi.vezhb2.stackVezhbi.zagradi;


import mk.ukim.finki.aps.kodovi.kod4_StackQueue.ArrayStack;

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


public class CorrectParentheses {


    public CorrectParentheses() {
    }

    public boolean checkParentheses(String line){

        ArrayStack<Character> stack = new ArrayStack<>(100);
        //(b-+[)]/2a
        for(int i=0; i<line.length(); i++){
            char curr = line.charAt(i);

            if (curr == '(' || curr == '[' || curr == '{') {
                stack.push(curr);
            } else if (curr == ')' || curr == ']' || curr == '}') {
                if(stack.isEmpty()) return false;
                char left = stack.pop();
                if(!check2(left,curr)) return false;
            }

        }
        return (stack.isEmpty());
    }

    public boolean check2(char left, char right) {
        switch (left) {
            case '(' : return (right == ')');
            case '{' : return (right == '}');
            case '[' : return (right == ']');
            default : return false;
        }
    }

    public static void main(String[] args) {


        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CorrectParentheses cp = new CorrectParentheses();
        System.out.println(cp.checkParentheses(line));


    }
}
