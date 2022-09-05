package mk.ukim.finki.aps.kodovi.kod4_StackQueue;

import java.util.NoSuchElementException;

public class LinkedStack<E> implements Stack<E> {

    //Stekot e pretstaven na sledniot nacin: top e link do prviot jazol
    // na ednostrano-povrzanata lista koja sodrzi gi elementite na stekot .
    private SLLNode<E> top;
    private int counter = 0;

    public LinkedStack () {
    	// Konstrukcija na nov, prazen stek.
        top = null;
    }

    public boolean isEmpty () {
    	// Vrakja true ako i samo ako stekot e prazen.
        return (top == null);
    }

    public int length(){
        return counter;
    }

    public E peek () {
    	// Go vrakja elementot na vrvot od stekot.
        if (top == null)
            throw new NoSuchElementException();
             //or return null;
        return top.element;
    }

    public void clear () {
    	// Go prazni stekot.
        top = null;
    }

    public void push (E x) {
    	// Go dodava x na vrvot na stekot.

        /*
        SLLNode<E> ins = new SLLNode<>(x,top);
          top = ins;
         */
        top = new SLLNode<E>(x, top);
        counter++;
    }

    public E pop () {
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
        while(top != null){
            sb.append(top).append(" ");
            top = top.succ;
        }
        return sb.toString();
    }


    public String toString2() {
        StringBuilder sb = new StringBuilder();
        while(top != null){
            if(top.succ == null){
                sb.append(top);
                break;
            } else {
                sb.append(top).append("->");
            }
            top = top.succ;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedStack<Integer> ls = new LinkedStack<>();

        //System.out.println(ls);
        ls.push(1);
        ls.push(2);
        ls.push(3);
        ls.push(4);

        System.out.println(ls.toString2());
        System.out.println(ls.peek());
        System.out.println(ls.length());

        /*
            DA VIDAM ZA LINKEDSTACK
         */

        System.out.println(ls.pop());
        System.out.println(ls.length());
        System.out.println();
        //System.out.println(ls.toString2());

    }

}
