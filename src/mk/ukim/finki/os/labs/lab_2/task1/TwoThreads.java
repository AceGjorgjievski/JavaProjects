package mk.ukim.finki.os.labs.lab_2.task1;


/*
Извршете го примерот од TwoThreads.
Потоа, модифицирајте ја програмата така што ќе користите само една класа за нитки, ThreadClassNumbersLetters.
Во конструкторот на класата ќе се предаде листа која соодветната инстанца треба да ја отпечати.
Нитката не треба да ја наследува класата Thread.
Однесувањето на новата програма треба да биде исто како на оригиналната,
односно повторно треба да имате две нитки кои ќе го извршуваат посебно методот run():
едната нитка ќе ги печати првите пет броеви, потоа другата ќе ги печати првите пет букви од англиската азбука,


 */


public class TwoThreads {

    public static void main(String[] args) throws InterruptedException {
        ThreadClassLetters letters = new ThreadClassLetters();
        ThreadClassNumbers numbers = new ThreadClassNumbers();

        ThreadClassNumbersLetters threadClassNumbersLetters1 = new ThreadClassNumbersLetters(numbers);
        ThreadClassNumbersLetters threadClassNumbersLetters2 = new ThreadClassNumbersLetters(letters);

//        numbers.start();
//        numbers.join();
//        letters.start();
//        letters.join();

    }
}

class ThreadClassNumbersLetters {
    ThreadClassNumbers threadClassNumbers;
    ThreadClassLetters threadClassLetters;

    public ThreadClassNumbersLetters(Thread thread) throws InterruptedException {
        if(thread instanceof ThreadClassLetters) {
            threadClassLetters = new ThreadClassLetters();
            threadClassLetters.start();
            threadClassLetters.join();
        } else {
            threadClassNumbers = new ThreadClassNumbers();
            threadClassNumbers.start();
            threadClassNumbers.join();
        }
    }
}

class ThreadClassNumbers extends Thread {

    @Override
    public void run() {
        for (int i=0; i<5; i++) System.out.println(i);
    }
}

class ThreadClassLetters extends Thread {

    @Override
    public void run() {
        for (int i=0; i<5; i++) System.out.println((char)(i + 65));
    }
}

