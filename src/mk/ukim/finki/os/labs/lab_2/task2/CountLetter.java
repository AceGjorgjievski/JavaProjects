package mk.ukim.finki.os.labs.lab_2.task2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.concurrent.Semaphore;

/*          Ne znam dali e ova, samo vo count() i init() beshe prazno i se drugo beshe ostanato vaka
Со помош на синхронизациските методи да се реши проблемот за определување на бројот на појавувања на буквата А
во  стрингот и негово запишување во глобална променлива count.

Секвенцијалното решение не е прифатливо поради тоа што трае многу долго време (поради големината на стрингот).
За таа цел, потребно е да се паралелизира овој процес,
при што треба да се напише метода која ќе ги брои појавувањата на буквата А во помал фрагмент од стрингот,
при што резултатот повторно се чува во глобалната заедничка променлива count.

Напомена: Почетниот код е даден во почетниот код CountLetter. Задачата да се тестира над стринг од минимум 1000 карактери.

 */


public class CountLetter {

    /**
     * Promenlivata koja treba da go sodrzi brojot na pojavuvanja na bukvata A
     */
    int count = 0;
    /**
     * TODO: definirajte gi potrebnite elementi za sinhronizacija
     */

    static Semaphore semaphore;

    public void init() {
        semaphore = new Semaphore(1);
    }

    class Counter extends Thread {

        public void count(String data) throws InterruptedException {
            // da se implementira
            semaphore.acquire();
            if(data.equals("A")) {
                count++;
            }
            semaphore.release();
        }
        private String data;

        public Counter(String data) {
            this.data = data;
        }

        @Override
        public void run() {
            try {
                count(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            CountLetter environment = new CountLetter();
            environment.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws Exception {

        init();

        HashSet<Thread> threads = new HashSet<Thread>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String pom = bf.readLine();
        String [] data = pom.split("");

        for(int i = 0; i< data.length; i++) {

            Counter c = new Counter(data[i]);
            threads.add(c);
        }


        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println(count);


    }
}