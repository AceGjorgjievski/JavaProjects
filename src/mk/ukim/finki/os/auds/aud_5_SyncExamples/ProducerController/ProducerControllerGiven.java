package mk.ukim.finki.os.auds.aud_5_SyncExamples.ProducerController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ProducerControllerGiven {
    public static int NUM_RUN = 50;


    public static Semaphore accessBuffer;
    public static Semaphore canCheck;
    public static Semaphore lock;

    public static int numChecks;


    public static void init() {
        accessBuffer = new Semaphore(1);
        canCheck = new Semaphore(10);
        lock = new Semaphore(1);
        numChecks = 0;
    }

    public static class Buffer {

        public void produce() {
            System.out.println("Producer is producing...");
        }

        public void check() {
            System.out.println("Controller is checking...");
        }
    }

    public static class Producer extends Thread {
        private final Buffer buffer;

        public Producer(Buffer b) {
            this.buffer = b;
        }

        public void execute() throws InterruptedException {
            accessBuffer.acquire();
            buffer.produce();
            accessBuffer.release();
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

    public static class Controller extends Thread {

        private final Buffer buffer;

        public Controller(Buffer buffer) {
            this.buffer = buffer;
        }

        public void execute() throws InterruptedException {
            lock.acquire();
            if(numChecks==0) {
                accessBuffer.acquire();
            }
            canCheck.acquire();
            numChecks++;
            lock.release();

            buffer.check();

            lock.acquire();
            numChecks--;
            canCheck.release();
            if(numChecks == 0){
                accessBuffer.release();
            }
            lock.release();

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


    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Producer p = new Producer(buffer);
        List<Controller> controllers = new ArrayList<>();
        init();
        for (int i = 0; i < 10; i++) {
            controllers.add(new Controller(buffer));
        }
        p.start();
        for (int i = 0; i < 10; i++) {
            controllers.get(i).start();
        }

    }

}
