package mk.ukim.finki.os.auds.aud_4_SyncRaceCondition.Library;

import java.util.ArrayList;
import java.util.List;

public class SyncLibrary {
    List<String> books = new ArrayList<>();
    int capacity;

    public SyncLibrary(int capacity) {
        this.capacity = capacity;
    }

    public void addBook(String book) throws InterruptedException {
        while(books.size() == capacity) {
            wait();
        }
        books.add(book);
        notifyAll();
    }

    public String borrowBook() throws InterruptedException {
        String book = "";
        while(books.size() == 0) {
            wait();
        }
        book = books.remove(0);
        notifyAll();
        return book;
    }
}
