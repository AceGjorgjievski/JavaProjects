package mk.ukim.finki.os.auds.aud_4_SyncRaceCondition.Library;

import java.util.ArrayList;
import java.util.List;

public class LibraryTest {

    public static void main(String[] args) throws InterruptedException {
        List<Member> members = new ArrayList<>();
        SemaphoreLibrary semaphoreLibrary = new SemaphoreLibrary(10);
        for(int i=0; i<10; i++) {
            members.add(new Member("Memmber: "+ i, semaphoreLibrary));
        }

        for(Member m : members) {
            m.start();
        }

        for(Member m : members) {
            m.join(1000);
        }

        System.out.println("End of MAIN");
    }
}

class Member extends Thread {
    private String name;
    private SemaphoreLibrary semaphoreLibrary;

    public Member(String name, SemaphoreLibrary semaphoreLibrary) {
        this.name = name;
        this.semaphoreLibrary = semaphoreLibrary;
    }

    @Override
    public void run() {
        for(int i=0; i<3; i++) {
            System.out.println("Member: " + i + " returns book.");
            try {
                semaphoreLibrary.returnBook("Book: " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i=0; i<2; i++) {
            System.out.println("Member: " + i + " borrows book.");
            try {
                semaphoreLibrary.borrowBook();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
