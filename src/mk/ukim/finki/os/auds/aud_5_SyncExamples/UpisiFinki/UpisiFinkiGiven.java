package mk.ukim.finki.os.auds.aud_5_SyncExamples.UpisiFinki;

import java.util.concurrent.Semaphore;

public class UpisiFinkiGiven {


    static Semaphore komisija;
    static Semaphore vlezi;
    static Semaphore zapishiLock;
    static Semaphore lock;
    static Semaphore ostaveno;
    static Semaphore done;

    public static void init() {
        komisija = new Semaphore(4);
        vlezi = new Semaphore(0);
        lock = new Semaphore(1);
        ostaveno = new Semaphore(0);
        done = new Semaphore(0);
    }

    public static class Clen extends Thread {

        public void execute() throws InterruptedException {
            komisija.acquire();

            int numStudents = 10;

            while (numStudents > 0) {
                vlezi.release();
                ostaveno.acquire();
                zapisi();
                done.release();
                numStudents--;
            }
            komisija.release();
        }

        public void zapisi() {
            System.out.println("Zapisuvam student...");
        }

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Student extends Thread {

        public void execute() throws InterruptedException {
            vlezi.acquire();
            ostaviDokumenti();
            ostaveno.release();
            done.acquire();
        }

        public void ostaviDokumenti() {
            System.out.println("Ostavam dokumenti...");
        }

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
