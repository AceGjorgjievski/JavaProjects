package mk.ukim.finki.aps.juni.Ispiti.Predmeti;

//import mk.ukim.finki.aps.kodovi.kod4_StackQueue.Stack;

/*
При учење за испити, Никола има навика да ги чува сите книги на еден куп, една врз друга. При пребарување на дадена книга која му е потребна, тој секогаш ги трга прво најгорните, една по една, се додека не ја земе книгата која му треба. Штом ќе ја извади таа книга, останатите кои биле над неа ги враќа во истиот редослед назад. Откако ќе го научи дадениот предмет, ја враќа книгата на врвот на купот.

Дадена е иницијалната поставеност на книгите на купот на Никола (во редослед одоздола нагоре). Дадени се и испитите по распоред на полагање. Ваша задача е да одредите колку пати секоја од книгите ќе биде извадена и ставена назад на купот.

Влез:

Во првата линија од влезот се дадени два броја: М, број на книги и N, број на испити.

Во втората линија од влезот се дадени имињата на книгите, подредени одоздола нагоре.

Во третата линија од влезот се дадени испитите кои се полагаат по редослед.

Излез:

За секоја книга да се испечати колку пати ќе биде земена и вратена назад на купот (еден „настан“ на земање-враќање на книгата се брои еднаш, не два пати). Имињата на книгите се печатат во исти редослед во кој биле дадени на влезот.

--------------------------------------------------------------------------------------

Пример влез:

7 3
APS OS Мrezhi AOK Objektno Strukturno Kalkulus
APS Objektno Мrezhi

Пример излез:
APS 3
OS 1
Мrezhi 2
AOK 2
Objektno 3
Strukturno 3
Kalkulus 3



Објаснување: За да ја извадиме книгата за АПС, треба да ги извадиме и вратиме назад сите останати книги во купот. Откако ќе завршиме, ја враќаме најгоре на купот. Наредно полагаме Објектно, па за стигнеме до таа книга, треба да ги тргнеме прво книгите за АПС, калкулус и структурно. Ја враќаме книгата за Објектно најодозгора. За да дојдеме до книгата за мрежи, треба да ги извадиме и вратиме сите книги освен таа за ОС. На крај ја враќаме книгата за мрежи најгоре.

Забелешка: Не може да има дупликати наслови на книги. Еден испит може да се појави повеќе пати. На излез имињата на книгите се печатат во исти редослед во кој биле дадени на влезот.





other
in
==
8 0
APS OS Мrezhi AOK Objektno Strukturno AnaKarenina Kalkulus

out
==
APS 0
OS 0
Мrezhi 0
AOK 0
Objektno 0
Strukturno 0
AnaKarenina 0
Kalkulus 0

 */


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


public class Main {

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
