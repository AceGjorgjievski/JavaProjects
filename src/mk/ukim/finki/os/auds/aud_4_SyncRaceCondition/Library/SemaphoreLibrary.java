package mk.ukim.finki.os.auds.aud_4_SyncRaceCondition.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


public class SemaphoreLibrary {
    private int capacity;
    private List<String> books;
    private Semaphore coordinator = new Semaphore(1);
    private Semaphore borrowBookSemaphore = new Semaphore(5);
    private Semaphore returnBookSemaphore = new Semaphore(5);

    public SemaphoreLibrary(int capacity) {
        this.capacity = capacity;
        books = new ArrayList<>();
    }

    public void returnBook(String book) throws InterruptedException {
        returnBookSemaphore.acquire();
        coordinator.acquire();

        while(books.size() == capacity) {
            coordinator.release();
            Thread.sleep(100);
            coordinator.acquire();
        }
        books.add(book);
        coordinator.release();

        borrowBookSemaphore.release();
    }

    public String borrowBook() throws InterruptedException {
        String book = "";
        borrowBookSemaphore.acquire();
        coordinator.acquire();

        while (books.size() == 0) {
            coordinator.release();
            Thread.sleep(100);
            coordinator.acquire();
        }
        book = books.remove(0);
        coordinator.release();

        returnBookSemaphore.release();

        return book;
    }
}