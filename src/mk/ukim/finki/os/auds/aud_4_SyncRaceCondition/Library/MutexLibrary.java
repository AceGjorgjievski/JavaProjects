package mk.ukim.finki.os.auds.aud_4_SyncRaceCondition.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexLibrary {
    private int capacity;
    public static Lock lock = new ReentrantLock();
    private List<String> books;

    public MutexLibrary(int capacity) {
        this.capacity = capacity;
        books = new ArrayList<>();
    }

    public String borrowBook() {
        String book = "";
        while (true) {
            lock.lock();
            if(books.size() > 0) {
                book = books.remove(0);
                lock.unlock();
                break;
            }
            lock.unlock();
        }
        return book;
    }

    public void returnBook(String book) {
        while(true) {
            lock.lock();
            if(books.size() < capacity) {
                books.add(book);
                lock.unlock();
                break;
            }
            lock.unlock();
        }
    }
}






