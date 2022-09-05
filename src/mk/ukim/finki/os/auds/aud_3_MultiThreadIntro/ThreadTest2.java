package mk.ukim.finki.os.auds.aud_3_MultiThreadIntro;

public class ThreadTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread ta = new A();
        Thread tb = new B();

        ta.start();
        tb.start();
        ta.join();
        tb.join();

        CountThread countThread = new CountThread();
        countThread.start();

        try {
            countThread.join();
            System.out.println(countThread.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("mk.ukim.finki.aps.juni.Ispiti.zad2Zagaduvanje.Main done!");
    }
}

class A extends Thread {
    @Override
    public synchronized void run() {
        for (int i=1; i<=10; i++) {
            System.out.println("A: " + i);
        }
        System.out.println("A done !");
    }
}


class B extends Thread {
    @Override
    public synchronized void run() {
        for (int i=-1; i>=-10; i--) {
            System.out.println("\t\tB: " + i);
        }
        System.out.println("B done !");
    }
}

class CountThread extends Thread {
    private long result;

    public long getResult() {
        return result;
    }

    private long count() {
        long r = 0;
        for (r = 0; r < 1000000; r++) ;
        return r;
    }

    public void run() {
        result = count();
    }

}