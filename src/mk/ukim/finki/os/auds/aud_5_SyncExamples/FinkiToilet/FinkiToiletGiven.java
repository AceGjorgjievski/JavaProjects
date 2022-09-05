package mk.ukim.finki.os.auds.aud_5_SyncExamples.FinkiToilet;

import java.util.concurrent.Semaphore;

public class FinkiToiletGiven {

    public static class Toilet {

        public void vlezi() {
            System.out.println("Vleguva...");
        }

        public void izlezi() {
            System.out.println("Izleguva...");
        }
    }

    public static Semaphore enterToilet;
    public static Semaphore lockM;
    public static Semaphore lockW;
    public static Semaphore action;

    public static int numM;
    public static int numW;



    public static void init() {
        enterToilet = new Semaphore(1);
        lockM = new Semaphore(1);
        lockW = new Semaphore(1);
        action = new Semaphore(1);

        numM = 0;
        numW = 0;
    }

    public static class Man extends Thread{

        private Toilet toilet;

        public Man(Toilet toilet) {
            this.toilet = toilet;
        }

        public void enter() throws InterruptedException {
            lockM.acquire();
            if(numM == 0) {
                enterToilet.acquire();
            }
            numM++;
            toilet.vlezi();
            lockM.release();
        }

        public void exit() throws InterruptedException {
            lockM.acquire();
            numM--;
            toilet.izlezi();
            if(numM==0) {
                enterToilet.release();
            }
            lockM.release();
        }

        @Override
        public void run() {
            super.run();
        }
    }

    public static class Woman extends Thread{

        private Toilet toilet;

        public Woman(Toilet toilet) {
            this.toilet = toilet;
        }

        public void enter() throws InterruptedException {

        }

        public void exit() throws InterruptedException {

        }

        @Override
        public void run() {
            super.run();
        }
    }

}
