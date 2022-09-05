package mk.ukim.finki.vezhbi.algoritmi.vezhb2.stackVezhbi.xmlCode;


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


public class CheckXML {
    /*
20
[Korisnik]
[Ime]
Jana
[/Ime]
[Prezime]
[Korisnik]
[Ime]
Ivan
[/Ime]
[Prezime]
[Prezime]
[MBR]
2807986455092
[/MBR]
[/Korisnik]
[/Prezime]
[MBR]
2908986455091
[/MBR]
[/Korisnik]



     */

    public static boolean matchingPair(String left, String right) {
       // [left]
        // [/left]

        left = left.substring(1,left.length()); //left]
        right = right.substring(2,right.length());//left]

//        System.out.println(left);
//        System.out.println(right);

        for (int i=0; i<left.length(); i++) {
            if(left.charAt(i) != right.charAt(i)) return false;
        }
        return true;
    }

   public static boolean checkXMLCode(String [] inputLines) {
       Stack<String> stack = new ArrayStack<>(100);

       for(int i=0; i<inputLines.length; i++) {
           String currentLine = inputLines[i];

           if(currentLine.charAt(0) == '[' && currentLine.charAt(1) != '/') {
               stack.push(currentLine);
           }
           if(currentLine.charAt(1) == '/') {
               if(stack.isEmpty()) return false;

               String lineFromStack = stack.pop();
//               System.out.println(matchingPair(lineFromStack,currentLine));
               if(!(matchingPair(lineFromStack,currentLine))) return false;

           }
       }
       return (stack.isEmpty());
   }


    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        String [] lines = new String[N];

        for(int i=0; i<N; i++) {
            lines[i] = bf.readLine();
        }

        System.out.println(checkXMLCode(lines));
    }

}
