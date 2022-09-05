package mk.ukim.finki.vezhbi.os.za_juni.aud_3;

public class ThreadExample1 {

    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource();
        Thread t1 = new CustomThread(sharedResource);
        Thread t2 = new Thread(new CustomRunnable(sharedResource));
        Thread t3 = new CustomThread(sharedResource);
        t1.start();
        t2.start();
        t2.join();
        t3.start();
        sharedResource.increment();
        int finalCounter = sharedResource.getCounter();
        t3.join();
        sharedResource.increment();
        System.out.println("main done");
    }
}

class SharedResource{
    private int counter;
    public SharedResource(){counter = 0;}
    public void increment(){counter++;}
    public int getCounter(){return counter;};
}

class CustomRunnable implements Runnable{
    private SharedResource resource;
    public CustomRunnable(SharedResource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        for(int i=0; i<3; i++) {
            this.resource.increment();
            System.out.print("R");
        }
    }
}

class CustomThread extends Thread {
    private SharedResource resource;
    public CustomThread(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for(int i=0; i<3; i++) {
            this.resource.increment();
            System.out.print("T");
        }
    }
}