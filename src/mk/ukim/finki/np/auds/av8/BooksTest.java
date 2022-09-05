package mk.ukim.finki.np.auds.av8;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

//class PriceAndTitleComparator implements Comparator<Book>{
//
//    @Override
//    public int compare(Book o1, Book o2) {
//        int res = o1.title.compareTo(o2.title);
//        if(res == 0){
//            return Float.compare(o1.price,o2.price);
//        }
//        return res;
//    }
//}
//
//class PriceComparator implements Comparator<Book> {
//
//    @Override
//    public int compare(Book o1, Book o2) {
//        int res = Float.compare(o1.price,o2.price);
//        if(res == 0){
//            return o1.title.compareTo(o2.title);
//        }
//        return res;
//    }
//}

class Book {
    String title;
    String category;
    float price;

    public Book(String title, String category, float price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) %.2f",title,category,price);
    }

//    @Override
//    public int compareTo(Book other) {
//        int res = this.title.compareTo(other.title);
//        if(res == 0){
//            return Float.compare(this.price,other.price);
//        }
//        return res;
//    }
}

class BookCollection {
    List<Book> bookList;

    public BookCollection() {
        bookList = new ArrayList<>();
    }

    final Comparator<Book> titleAndPriceComparator =
            Comparator.comparing(Book::getTitle)
                    .thenComparing(Book::getPrice);

    final Comparator<Book> priceComparator =
            Comparator.comparing(Book::getPrice)
                    .thenComparing(Book::getTitle);

    public void printByCategory(String category) {
        bookList.stream()
                .filter(i -> i.category.equalsIgnoreCase(category))
                .sorted(titleAndPriceComparator)
                .forEach(i -> System.out.println(i));
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public List<Book> getCheapestN(int n) {
        return bookList.stream()
                .sorted(priceComparator)
                .limit(n)
                .collect(Collectors.toList());
    }
}

public class BooksTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        BookCollection booksCollection = new BookCollection();
        Set<String> categories = fillCollection(scanner, booksCollection);
        System.out.println("=== PRINT BY CATEGORY ===");
        for (String category : categories) {
            System.out.println("CATEGORY: " + category);
            booksCollection.printByCategory(category);
        }
        System.out.println("=== TOP N BY PRICE ===");
        print(booksCollection.getCheapestN(n));
    }

    static void print(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    static TreeSet<String> fillCollection(Scanner scanner,
                                          BookCollection collection) {
        TreeSet<String> categories = new TreeSet<String>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            Book book = new Book(parts[0], parts[1], Float.parseFloat(parts[2]));
            collection.addBook(book);
            categories.add(parts[1]);
        }
        return categories;
    }
}
