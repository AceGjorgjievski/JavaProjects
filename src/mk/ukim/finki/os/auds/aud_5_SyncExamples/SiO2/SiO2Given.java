package mk.ukim.finki.os.auds.aud_5_SyncExamples.SiO2;

import java.util.concurrent.Semaphore;

public class SiO2Given {

    public static int NUM_RUN = 50;


    public static Semaphore si_Enter;
    public static Semaphore o_Enter;
    public static Semaphore lock;

    public static int numO2;

    public static Semaphore siHere;
    public static Semaphore oHere;
    public static Semaphore ready;
    public static Semaphore done;

    public static void init() {
        si_Enter = new Semaphore(1);
        o_Enter = new Semaphore(2);
        lock = new Semaphore(1);

        numO2 = 0;

        siHere = new Semaphore(0);
        oHere = new Semaphore(0);
        ready = new Semaphore(0);
        done = new Semaphore(0);

    }

    public static class Si extends Thread {

        public void bond() {
            System.out.println("Si is bonding now.");
        }

        @Override
        public void run() {
            for (int i = 0; i < NUM_RUN; i++) {
                try {
                    execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void execute() throws InterruptedException {
            //TODO:
            si_Enter.acquire();
            siHere.release(2);
            oHere.acquire(2);
            ready.release(2);

            bond();

            si_Enter.release();
        }

    }

    public static class O extends Thread {

        public void execute() throws InterruptedException {
            //TODO:
            o_Enter.acquire();
            siHere.acquire();
            oHere.release();
            ready.acquire();

            bond();

            o_Enter.release();


        }

        public void bond() {
            System.out.println("O is bonding now.");
        }


        @Override
        public void run() {
            for (int i = 0; i < NUM_RUN; i++) {
                try {
                    execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
