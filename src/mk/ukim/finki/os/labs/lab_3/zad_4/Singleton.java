package mk.ukim.finki.os.labs.lab_3.zad_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//https://kkjavatutorials.com/singleton-design-pattern-in-java/

public class Singleton{

    private static volatile Singleton singleton;

    private Singleton() {

    }

    public static Singleton getInstance() {
        // TODO: 3/29/20 Synchronize this
        synchronized (Singleton.class) {
            singleton = new Singleton();

            return singleton;
        }
    }

    public static void main(String[] args) {
        // TODO: 3/29/20 Simulate the scenario when multiple threads call the method getInstance
        Random random = new Random();

        List<A> list = new ArrayList<>();
        for(int i=0; i<5; i++) {
            list.add(new A(random.nextInt(10)));
        }

        for(A a : list) {
            a.start();
        }

        System.out.println("done");


    }

}

class A extends Thread {

    private int id;

    public A(int id) {
        this.id = id;
    }


    public int getid() {
        return id;
    }

    @Override
    public void run() {
        Singleton.getInstance();
    }
}