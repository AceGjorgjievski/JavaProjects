package mk.ukim.finki.aps.juni.Additional_tasks.Additional_Task_2;




import java.util.*;

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
    public int depth;

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

    public int getDepth() {
        return depth;
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

class Subject {
    private String name;
    private int value;

    public Subject(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void increaseValue() {
        this.value += 1;
    }
}


public class Subjects {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lineNumbers = sc.nextLine();
        String [] partsNumbers = lineNumbers.split("\\s+");
        int M = Integer.parseInt(partsNumbers[0]);
        int N = Integer.parseInt(partsNumbers[1]);

        //Map<String, Integer> map = new HashMap<>();

        List<Subject> subjectList = new ArrayList<>();

        String line = sc.nextLine();
        String lineToStudy = sc.nextLine();

        String [] subjects = line.split("\\s+");//site predmeti
        String [] subjectsNeeded = lineToStudy.split("\\s+");//ti shto mi trebaat

        for(int i=0; i<M; i++) {
            subjectList.add(new Subject(subjects[i],0));
        }

        Stack<Subject> subjectsStack = new ArrayStack<Subject>(M);
        Stack<Subject> tempStack = new ArrayStack<Subject>(M);

        for(int i=0; i<M; i++) {
            subjectsStack.push(subjectList.get(i));
        }

        if(subjectsNeeded.length != 0) {
            for(int i=0; i<N; i++) {
                String getSubject = subjectsNeeded[i];//tie shto mi trebaat
                for(int j=0; j<M; j++) {//site predmeti
                    Subject poppedSubject = subjectsStack.pop();

                    poppedSubject.increaseValue();
                    if(getSubject.equals(poppedSubject.getName())) {
                        if(tempStack.isEmpty()) {
                            subjectsStack.push(poppedSubject);
                            break;
                        } else {
                            while(!tempStack.isEmpty()) {
                                subjectsStack.push(tempStack.pop());
                            }
                            subjectsStack.push(poppedSubject);
                            break;
                        }
                    } else {
                        tempStack.push(poppedSubject);
                    }
                }
            }
        }



        for(int i=0; i<subjectList.size(); i++) {
            System.out.println(subjectList.get(i).getName() + " " + subjectList.get(i).getValue());
        }

    }
}
