package mk.ukim.finki.os.auds.aud_5_SyncExamples.Ca3N2;

/**
 * https://github.com/ristes/synchronization-examples/tree/master/src/mk/ukim/finki/os/synchronization/exam14/march/ca3n2
 */

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

class CalciumNitrideState {


    public void bond(){}
    public void validate(){}
}


public class CalciumNitride {



    public static Semaphore caEnter;
    public static Semaphore niEnter;

    public static Semaphore niHere;
    public static Semaphore ready;
    public static Semaphore bondingDone;
    public static Semaphore canLeave;

    public static Semaphore lock;
    public static int caTotal;


    public static void init() {
        caEnter = new Semaphore(3);
        niEnter = new Semaphore(2);

        niHere = new Semaphore(0);
        ready = new Semaphore(0);
        bondingDone = new Semaphore(0);
        canLeave = new Semaphore(0);

        lock = new Semaphore(1);
        caTotal = 0;
    }

    public static class Calcium extends Thread {

        public Calcium(int numRuns) {
           // super(numRuns);
        }

        //@Override
        public void execute() throws InterruptedException {
            caEnter.acquire();
            lock.acquire();
            caTotal++;
            if(caTotal==3) {
                caTotal = 0;
                lock.release();

                niHere.acquire(2);
                ready.release(4);

                state.bond();

                bondingDone.acquire(4);
                canLeave.release(4);

                state.validate();
                caEnter.release();
            } else {
                lock.release();
                ready.acquire();

                state.bond();

                bondingDone.release();
                canLeave.acquire();

                caEnter.release();
            }
        }

    }

    public static class Nitrogen extends Thread {

        public Nitrogen(int numRuns) {
            //super(numRuns);
        }

       // @Override
        public void execute() throws InterruptedException {
            niEnter.acquire();
            niHere.release();

            ready.acquire();

            state.bond();

            bondingDone.release();
            canLeave.acquire();
            niEnter.release();
        }

    }

    static CalciumNitrideState state = new CalciumNitrideState();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            run();
        }
    }

    public static void run() {
        try {
            Scanner s = new Scanner(System.in);
            int numRuns = 1;
            int numIterations = 100;
            s.close();

            HashSet<Thread> threads = new HashSet<Thread>();

            for (int i = 0; i < numIterations; i++) {
                Nitrogen n = new Nitrogen(numRuns);
                threads.add(n);
                Calcium ca = new Calcium(numRuns);
                threads.add(ca);
                ca = new Calcium(numRuns);
                threads.add(ca);
                n = new Nitrogen(numRuns);
                threads.add(n);
                ca = new Calcium(numRuns);
                threads.add(ca);
            }

            init();

            //ProblemExecution.start(threads, state);
            System.out.println(new Date().getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}


