package mk.ukim.finki.aps.labs.kolokviumski1.MatIzraz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear();
    // Go prazni stekot.

    public void push(E x);
    // Go dodava x na vrvot na stekot.

    public E pop();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
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
        return elems[depth - 1];
    }


    public void clear() {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++) elems[i] = null;
        depth = 0;
    }


    public void push(E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop() {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            //throw new NoSuchElementException();
            return null;
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression) {
        // Vasiot kod tuka
        Stack<Integer> stackAddition = new ArrayStack<>(expression.length());
        Stack<Integer> stackMultiplication = new ArrayStack<>(expression.length());

        char[] array = expression.toCharArray();

        int num = 0;
        for (int i = 0; i < array.length; i++) {
            char curr = array[i];
            //2+2*2*2+2

            if(Character.isDigit(curr) && (i+1 == array.length)) { // posleden broj/cifra
                num = (num*10) + Character.getNumericValue(curr);
                String line = String.valueOf(num);
                int lenghtOfCurr = line.length();

                if((array[i-lenghtOfCurr]) == '*') {//dali prethodniot znak e *
                    int num2 = stackMultiplication.pop();
                    stackAddition.push(num*num2);
                } else if((array[i-lenghtOfCurr]) == '+') {// dalie prethodniot znak e +
                    int num2 = stackAddition.pop();
                    stackAddition.push(num+num2);
                }
            } else if (Character.isDigit(curr)) {//dali e cifra
                num = (num * 10) + Character.getNumericValue(curr);
            } else if (!Character.isDigit(curr)) {//dali ne e cifra
                if(curr == '*') {
                    if(!stackMultiplication.isEmpty()) {
                        int num2 = stackMultiplication.pop();
                        stackMultiplication.push(num*num2);
                    } else {
                        stackMultiplication.push(num);
                    }
                } else if(curr == '+') {
                    String line = String.valueOf(num);//formiraj string of brojot
                    int lenOfCurr = line.length();//za da ja zemam dolzhinata na brojot
                    if((i-lenOfCurr-1 < 0)) {//ako e prv broj
                        if(!(stackAddition.isEmpty())) {//ako e poln stekot
                            int num2 = stackAddition.pop();
                            stackAddition.push(num+num2);
                        } else {
                            stackAddition.push(num);
                        }//2+2+2*2
                    } else if((array[i-lenOfCurr-1] == '*')) {//znachi brojot e nekade vo sredina i dali prethodniot znak e *
                        if(!(stackMultiplication.isEmpty())) {
                            int num2 = stackMultiplication.pop();

                            if(!stackAddition.isEmpty()) {
                                int num3 = stackAddition.pop();
                                num2 *= num;
                                stackAddition.push(num3+num2);
                            } else {
                                stackAddition.push(num2*num);
                            }
                        }
                    } else if((array[i-lenOfCurr-1] == '+')) {//znachi brojot e nekade vo sredina i dali prethodniot znak e *
                        if(!(stackAddition.isEmpty())) {
                            //2*2+2+2*2
                            int num2 = stackAddition.pop();
                            stackAddition.push(num+num2);
                        }
                    }
                }
                num = 0;
            }
        }

        int finalCount = 0;
        while(!stackAddition.isEmpty()) {
            finalCount += stackAddition.pop();
        }
        return finalCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println(evaluateExpression(input.readLine()));

//        String [] line = input.readLine().split("\\+");
//
//        for(int i=0; i<line.length; i++) {
//            String [] mnozhenje = line[i].split("\\*");
//            for(int j=0; j<mnozhenje.length; j++) {
//                System.out.print(mnozhenje[j] + " ");
//            }
//        }
//        ovde eden stek i da napravi proizvod i sobirok


//        int num = 123;
//        String line = String.valueOf(num);
//        char [] array = line.toCharArray();
//        System.out.println(array.length);
    }

}