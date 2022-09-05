package mk.ukim.finki.vezhbi.os.za_juni.aud_3;

public class ThreadTestJune {

    public static void main(String[] args) throws InterruptedException {
        T1 thread1 = new T1("T1");
        T2 thread2 = new T2("T2");
        T2 thread3 = new T2("T3");

        thread1.start();
        thread2.start();
        thread2.join();
        //System.out.println(thread1.getCount());
//        thread1.join();
//
//        thread2.start();
//        thread2.join();
//        if(thread1.isAlive() && thread2.isAlive()) {
//            System.out.println("T1 is alive");
//            thread1.interrupt();
//            thread2.interrupt();
//        }



        System.out.println("MAIN done!");
    }
}

class T1 extends Thread {
    String name;
    int count = 0;
    public T1(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            System.out.println(name+": Hello "+i);
        }
    }
}

class T2 extends Thread {
    String name;

    public T2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            System.out.println("\t" + name+": Hello "+i);
        }
    }
}

class T3 extends Thread {
    String name;

    public T3(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            System.out.println("\t" + name+": Hello "+i);
        }
    }
}
