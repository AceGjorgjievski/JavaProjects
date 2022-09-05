package mk.ukim.finki.os.auds.aud_4_SyncRaceCondition.Alternate;

import java.util.HashSet;
import java.util.Scanner;

/**             GIVEN
 * Write a program which will allow the execution of the f1() and f2() functions alternately
 * (one after the other, in an alternating fashion). Their calls should be implemented in threads,
 * such that the first thread will call the f1() function non-stop, and the second thread will call f2()
 * function non-stop. Apart from securing that the functions will be called alternately, you need to
 * enable the execution of f1() more times (extra times) over f2(),
 * while this number is not greater than N.
 */


public class AlternateExecutionGiven {

    public static int NUM_RUNS = 10;

    //todo
    public void init(int count){

    }

    class F1Thread extends Thread {

        //todo: complete
        public void executeF1() {
            f1();
        }

        @Override
        public void run() {
            try{
                executeF1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class F2Thread extends Thread {

        //todo: complete
        public void executeF2(){
            f2();
        }

        @Override
        public void run() {
            try {
                executeF2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void f1() {
        System.out.println("f1()");
    }

    public void f2() {
        System.out.println("f2()");
    }

    public void start() throws Exception {
        System.out.println("Enter the number of extra executions of f1()");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        init(n);

        HashSet<Thread> threads = new HashSet<Thread>();
        for (int i = 0; i < NUM_RUNS; i++) {
            F1Thread f1 = new F1Thread();
            F2Thread f2 = new F2Thread();
            threads.add(f1);
            threads.add(f2);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }


    public static void main(String[] args) {
        try {
            AlternateExecutionSolution enviroment = new AlternateExecutionSolution();
            enviroment.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
