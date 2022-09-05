package mk.ukim.finki.os.labs.lab_3.zad_5;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class Vinegar {

    public static void main(String[] args) throws InterruptedException {
        HashSet<Thread> threads = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            threads.add(new C());
        }

        for(int i=0; i<40; i++) {
            threads.add(new O());
        }

        for(int i=0; i<20; i++) {
            threads.add(new H());
        }
        init();
        // run all threads in background
        for(Thread t : threads) {
            t.start();
        }


        for (Thread t : threads) {
            //try {
                t.join(2000);
           // } catch (InterruptedException e) {
                 if(t.isAlive()) {
                     t.interrupt();
                     System.out.println("Possible deadlock!");
                }
            //}
        }

        // after all of them are started, wait each of them to finish for maximum 2_000 ms

        // for each thread, terminate it if it is not finished

        System.out.println("Process finished.");

    }

    public static Semaphore cEnter;
    public static Semaphore hEnter;
    public static Semaphore oEnter;

    public static Semaphore lock;
    public static int hCounter;

    public static Semaphore oHere;
    public static Semaphore cHere;
    public static Semaphore ready;
    public static Semaphore bondingDone;
    public static Semaphore canLeave;

    public static void init() {
        cEnter = new Semaphore(2);
        hEnter = new Semaphore(4);
        oEnter = new Semaphore(2);

        lock = new Semaphore(1);
        hCounter = 0;

        oHere = new Semaphore(0);
        cHere = new Semaphore(0);
        ready = new Semaphore(0);
        bondingDone = new Semaphore(0);
        canLeave = new Semaphore(0);

    }

    static class C extends Thread {

        @Override
        public void run() {
            try {
                this.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void execute() throws InterruptedException {
            // at most 2 atoms should print this in parallel
            cHere.acquire();
            System.out.println("C here.");
            // after all atoms are present, they should start with the bonding process together
            ready.acquire();
            System.out.println("Molecule bonding.");
            Thread.sleep(100);// this represent the bonding process

            bondingDone.release();
            System.out.println("C done.");

            canLeave.acquire();
            cHere.release();
        }
    }

    static class H extends Thread {

        @Override
        public void run() {
            try {
                this.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void execute() throws InterruptedException {
            // at most 4 atoms should print this in parallel

            //crtezh -> https://prnt.sc/WJSeiGPRulCq

            hEnter.acquire();
            lock.acquire();
            hCounter++;
            if(hCounter==4) {
                hCounter = 0;
                lock.release();
                System.out.println("H here.");

                oHere.acquire(2);
                cHere.acquire(2);
                ready.release(7);
                System.out.println("Molecule bonding.");
                Thread.sleep(100);// this represent the bonding process

                bondingDone.acquire(7);
                System.out.println("H done.");

                System.out.println("Molecule created.");
                canLeave.release(7);

                hEnter.release();
            } else {
                lock.release();
                System.out.println("H here.");

                ready.acquire();
                System.out.println("Molecule bonding.");
                Thread.sleep(100);// this represent the bonding process

                bondingDone.release();
                System.out.println("H done.");

                canLeave.acquire();
                hEnter.release();
            }
        }
    }

    static class O extends Thread {

        @Override
        public void run() {
            try {
                this.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void execute() throws InterruptedException {
            // at most 2 atoms should print this in parallel
            oEnter.acquire();
            System.out.println("O here.");
            // after all atoms are present, they should start with the bonding process together
            ready.acquire();
            System.out.println("Molecule bonding.");
            Thread.sleep(100);// this represent the bonding process

            bondingDone.release();
            System.out.println("O done.");

            canLeave.acquire();
            oEnter.release();
        }
    }

}