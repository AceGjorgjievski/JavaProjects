package mk.ukim.finki.os.labs.lab_2.task3;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.Semaphore;


/*
Симулирајте сценарио каде повеќе нишки истовремено додаваат и одземаат елементи од една инстанца од типот BlockingQueue. Синхронизирајте го пристапот до enqueue и dequeue со користење на синхронизациски механизам со помош на Mutex.

----

Simulate a scenario where multiple concurrent threads enqueue and dequeue elements from one instance of the type BlockingQueue. Synchronize the access to enqueue and dequeue using a synchronization mechanism with the help of Mutex.



public class BlockingQueue<T> {

    T[] contents;
    int capacity;

    public BlockingQueue(int capacity) {
        contents = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public void enqueue(T item) {
    }

    public T dequeue() {
    }
}

 */

public class BlockingQueue<T> {

    T[] contents;
    int capacity;
    public static Semaphore lock1 = new Semaphore(1);
    public static Semaphore lock2 = new Semaphore(0);
    int front,rear,length;

    public BlockingQueue(int capacity) {
        contents = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public boolean isFull() {
        return this.length == this.capacity;
    }

    public void enqueue(T item) throws InterruptedException {
//        lock1.acquire();
//        if (this.capacity == this.length) {
//            lock2.release();
//            Thread.sleep(1000);
//        }
//        this.contents[rear++] = item;
//        if(rear == contents.length) rear = 0;
//        length++;
//        System.out.println("Added: "+item);
//        lock1.release();








        while (true) {
//            lock1.acquire();
            if(this.length == this.capacity) {
//                lock2.release();
                wait();
            }
            contents[rear++] = item;
            if (rear == contents.length)  rear = 0;
            length++;

            System.out.println("Added: " + item);
            notify();
            Thread.sleep(1000);
        }

    }

    public T dequeue() throws InterruptedException {

//        lock2.acquire();
//        if(this.length == 0) {
//            lock1.release();
//            Thread.sleep(1000);
//        }
//        T topmost = contents[front];
//        contents[front++] = null;
//        if (front == contents.length)  front = 0;
//        length--;
//        System.out.println("Removed: "+topmost);
//        lock2.release();
//        return topmost;




        while(true) {
//            lock2.acquire();
            if(this.length == 0) {
//                lock1.release();
                wait();
            }
            T frontmost = contents[front];
            contents[front++] = null;
            if (front == contents.length)  front = 0;
            length--;
            System.out.println("\t\tRemoved: "+frontmost);
            notify();
            Thread.sleep(1000);
//            lock2.release();
            return frontmost;
        }

    }


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> integerBlockingQueue = new BlockingQueue<>(10);
        AddNumber addNumber = new AddNumber(integerBlockingQueue);
        RemoveNumber removeNumber = new RemoveNumber(integerBlockingQueue);

        addNumber.start();
        removeNumber.start();

        addNumber.join();
        removeNumber.join();



    }

}

class AddNumber extends Thread {
    BlockingQueue<Integer> addNumberToQueue;
    Random random = new Random();

    public AddNumber(BlockingQueue<Integer> addNumberToQueue) {
        this.addNumberToQueue = addNumberToQueue;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++) {
            try {
                addNumberToQueue.enqueue(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class RemoveNumber extends Thread{
    BlockingQueue<Integer> removeNumberFromQueue;

    public RemoveNumber(BlockingQueue<Integer> removeNumberFromQueue) {
        this.removeNumberFromQueue = removeNumberFromQueue;
    }

    @Override
    public void run() {
        for(int i=0; i<10;i++){
            try {
                removeNumberFromQueue.dequeue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
