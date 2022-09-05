package mk.ukim.finki.os.auds.aud_4_SyncRaceCondition.Alternate;

import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Alternately {

    public static int NUM_RUNS = 1000;

    int f1count;
    int f2count;
    int maxDifference = 0;

    Semaphore s1;
    Semaphore s2;

    /**
     * A method for initializing the semaphore values and
     * the other variables for synchronization.
     *
     */
    public void init(int count) {
        // implement this

        s1 = new Semaphore(count);
        s2 = new Semaphore(0);
    }


    class F1Thread extends Thread {

        public void executeF1() throws InterruptedException {
            // implement this

            s1.acquire();
            f1();
            s2.release();
        }

        @Override
        public void run() {
            try {
                executeF1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    class F2Thread extends Thread {

        public void executeF2() throws InterruptedException {
            // implement this

            s2.acquire();
            f2();
            s1.release();
        }

        @Override
        public void run() {
            try {
                executeF2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void f1() {
        System.out.println("f1()");
        f1count++;
        if (f1count - f2count > maxDifference) {
            maxDifference = f1count - f2count;
        }
    }

    public void f2() {
        System.out.println(" ");
        f2count++;

        if (f1count - f2count > maxDifference) {
            maxDifference = f1count - f2count;
        }
    }

    public static void main(String[] args) {
        try {
            Alternately environment = new Alternately();
            environment.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws Exception {

        System.out.println("Enter the number of extra executions of f1()");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        init(n);

        HashSet<Thread> threads = new HashSet<Thread>();
        for (int i = 0; i < NUM_RUNS; i++) {
            F1Thread f1 = new F1Thread();
            F2Thread f2 = new F2Thread();
            threads.add(f1);
            threads.add(f2);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println("F1count: " + f1count);
        System.out.println("F2count: " + f2count);
        System.out.println("maxDifference: " + maxDifference);
        System.out.println("Status: " + (maxDifference <= n));
    }
}

