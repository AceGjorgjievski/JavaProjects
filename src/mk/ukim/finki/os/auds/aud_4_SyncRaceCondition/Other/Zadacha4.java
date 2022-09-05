package mk.ukim.finki.os.auds.aud_4_SyncRaceCondition.Other;

public class Zadacha4 {

    public static void main(String[] args) throws InterruptedException {
        MemoryVisibility mv = new MemoryVisibility();
        Thread thread1 = new Thread(() -> {
            try {
                mv.thread1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            mv.thread2();
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}

class MemoryVisibility {
    Object o;
    int value = 2;
    boolean done = false;

    void thread1() throws InterruptedException {
        synchronized (this) {
            while (!done) {
                wait();
                System.out.println(value);
            }
        }
    }

    void thread2() {
        synchronized (this) {
            value = 5;
            done = true;
            notifyAll();
        }
    }
}
