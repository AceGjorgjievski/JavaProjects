package mk.ukim.finki.os.labs.lab_3.zad2;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class BasketballTournament {

    public static void main(String[] args) throws InterruptedException {
        HashSet<Player> threads = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            Player p = new Player();
            p.init();
            threads.add(p);
        }
        // run all threads in background
        for(Thread t : threads) {
            t.start();
        }

        // after all of them are started, wait each of them to finish for maximum 5_000 ms
        for(Thread t : threads) {
            t.join(100);
            if(t.isAlive()) {
                System.out.println("Possible deadlock!");
                t.interrupt();
            }
        }

        // for each thread, terminate it if it is not finished
        System.out.println("Tournament finished.");

    }
}

class Player extends Thread{


    public static Semaphore enterTournament;
    public static Semaphore enterDressingRoom;

    public static int playersEntered;
    public static Semaphore lock;

    public static Semaphore gameBegin;

    public static int playersDone;
    public static Semaphore lockDone;


    public void init() {
        enterTournament = new Semaphore(20);
        enterDressingRoom = new Semaphore(10);

        playersEntered = 0;
        lock = new Semaphore(1);
        gameBegin = new Semaphore(0);

        playersDone = 0;
        lockDone = new Semaphore(1);
    }

    public Player(){
        //this.init();
    }

    @Override
    public void run() {
        try {
            this.execute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void execute() throws InterruptedException {
        // at most 20 players should print this in parallel

        //crtez -> https://prnt.sc/Xdyjl1QtdTFI

        enterTournament.acquire();
        System.out.println("Player inside.");
        // at most 10 players may enter in the dressing room in parallel

        enterDressingRoom.acquire();
        lock.acquire();
        playersEntered++;
        lock.release();
        System.out.println("In dressing room.");
        Thread.sleep(10);// this represent the dressing time
        enterDressingRoom.release();

        lock.acquire();
        playersEntered--;
        if(playersEntered == 0) {
            gameBegin.release(20);
        }
        lock.release();
        gameBegin.acquire();
        // after all players are ready, they should start with the game together
        System.out.println("Game started.");
        Thread.sleep(100);// this represent the game duration

        lockDone.acquire();
        playersDone++;
        if(playersDone == 20) {
            // only one player should print the next line, representing that the game has finished
            playersDone = 0;
            System.out.println("Game finished.");
            enterTournament.release(20);
        } else {
            System.out.println("Player done.");
        }
        lockDone.release();
    }
}