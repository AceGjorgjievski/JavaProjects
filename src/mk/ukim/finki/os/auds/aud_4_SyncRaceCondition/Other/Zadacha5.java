package mk.ukim.finki.os.auds.aud_4_SyncRaceCondition.Other;

import java.util.ArrayList;
import java.util.List;


public class Zadacha5 {

    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource();
        Producer producer = new Producer(sharedResource);
        Consumer consumer = new Consumer(sharedResource);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("End of MAIN");
    }
}

class SharedResource {
    List<Integer> list = new ArrayList<>();
    int capacity = 9;

    public void produce() throws InterruptedException {
        int val = 1;

        synchronized (this) {
            while (true) {
                while (list.size() == capacity) {
                    wait();
                }
                System.out.println("Producer : " + val);

                list.add(val++);
                if (val == 10) val = 1;

                notify();
                Thread.sleep(600);
            }
        }
    }

    public void consume() throws InterruptedException {
        int val;
        while (true) {
            synchronized (this) {
                while (list.size() == 0) {
                    wait();
                }
                val = list.remove(0);
                System.out.println("\t\t\tConsumer : " + val);
                notify();
                Thread.sleep(600);
            }
        }
    }
}

class Producer extends Thread {
    SharedResource sharedResource;

    public Producer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        try {
            sharedResource.produce();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer extends Thread {
    SharedResource sharedResource;

    public Consumer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        try {
            sharedResource.consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
















