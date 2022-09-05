package mk.ukim.finki.os.auds.aud_3_MultiThreadIntro;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest{

    public static void main(String[] args) throws InterruptedException {
//        System.out.println("MAIN");
        Incrementor incrementor1 = new Incrementor();
        Incrementor incrementor2 = new Incrementor();

        ThreadClass t1 = new ThreadClass("T1",incrementor1);
        ThreadClass t2 = new ThreadClass("T2",incrementor2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        if(t1.isAlive() && t2.isAlive()) {
            System.out.println("Threads are alive");
            t1.interrupt();
            t2.interrupt();
        }

          System.out.println(incrementor1.getCount());
        System.out.println(incrementor2.getCount());
    }
}

class ThreadClass extends Thread {
    private String name;
    private Incrementor incrementor;

    public ThreadClass(String name, Incrementor incrementor) {
        this.name = name;
        this.incrementor = incrementor;
    }

    @Override
    public void run() {
//        System.out.println("THREAD");
        for(int i=0; i<20; i++) {
//            System.out.println("Thread " + name + ": " + i);

            try {
                incrementor.unsafeIncrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class Incrementor{
    private int count = 0;
    private static Lock lock = new ReentrantLock();

    public void unsafeIncrement() throws InterruptedException {
        count++;
        Thread.sleep(1);
    }

    public synchronized void safeIncrement() {
        count++;
//        synchronized (this) {
//            count++;
//        }
    }

    public void safeClassIncrement() {
        synchronized (Incrementor.class) {
            count++;
        }
    }

    public void safeMutexIncrement() {
        lock.lock();
        count++;
        lock.unlock();//mora unlock, vo sprotivno nema da zavrshi programata, se gubi kluchot i
        //site drugi threads chekaat da dojde kluch, a toj e izguben t.e. deadlock koga site chekaat
    }

    public int getCount() {
        return count;
    }
}