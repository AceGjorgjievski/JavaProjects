package mk.ukim.finki.os.auds.aud_5_SyncExamples.Carnival;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

/**
 * https://github.com/ristes/synchronization-examples/tree/master/src/mk/ukim/finki/os/synchronization/exam16/k1/g2
 */

public class Carnival {

    public static int GROUP_SIZE = 10;
    public static int TOTAL = 30;

    public static Semaphore enterStage;
    public static int stageCount;

    public static Semaphore lock;
    public static Semaphore startPerforming;

    public static int totalCount;
    public static Semaphore endCycle;

    public static void init() {
        enterStage = new Semaphore(10);
        stageCount = 0;
        lock = new Semaphore(1);
        startPerforming = new Semaphore(0);
        totalCount = 0;
        endCycle = new Semaphore(0);
    }

    public static class Participant extends Thread {

        public Participant(int numRuns) {
            //super(numRuns);
        }

        //@Override
        public void execute() throws InterruptedException {
            enterStage.acquire();

            lock.acquire();
            stageCount++;
            if(stageCount == GROUP_SIZE) {
                //stageCount = 0;
                startPerforming.release(GROUP_SIZE);
            }
            lock.release();
            startPerforming.acquire();

            state.present();

            lock.acquire();
            stageCount--;
            if(stageCount == 0) {
                state.endGroup();
                enterStage.release(10);
            }

            totalCount++;
            if(totalCount == TOTAL) {
                totalCount = 0;
                state.endCycle();
                endCycle.release(TOTAL);
            }
            lock.release();
            endCycle.acquire();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            run();
        }
    }

    static CarnivalState state = new CarnivalState();

    public static void run() {
        try {
            int numRuns = 15;
            int numThreads = 30;

            HashSet<Thread> threads = new HashSet<Thread>();

            for (int i = 0; i < numThreads; i++) {
                Participant c = new Participant(numRuns);
                threads.add(c);
            }

            init();

           // ProblemExecution.start(threads, state);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}