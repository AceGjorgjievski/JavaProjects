package InterviewQuestions.sorsixIntership.internship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class PrintWorkTime {
    int start;
    int end;

    public PrintWorkTime(int start, int end) {
        this.start = start;
        this.end = end;
    }


    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}

class Printer {
    private String name;
    private double speed;
    private String workTime;
    private boolean isColor;

    public Printer(String name, double speed, String workTime, boolean isColor) {
        this.name = name;
        this.speed = speed;
        this.workTime = workTime;
        this.isColor = isColor;
    }

    //P1 1 8-16 BW/RGB
    public static Printer create(String line) {
        String [] parts = line.split(" ");
        String name = parts[0];
        double speed = Integer.parseInt(parts[1]);
        String time = parts[2];
        boolean color = parts[3].equals("RGB");
        return new Printer(name, speed, time, color);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public boolean isColor() {
        return isColor;
    }

    public void setColor(boolean color) {
        isColor = color;
    }

    public boolean hasWorkTime() {
        String [] parts = this.workTime.split("-");
        int start = Integer.parseInt(parts[0]);
        int end = Integer.parseInt(parts[1]);

        int calculate = end - start;

        return calculate == 8;

    }

    public boolean canPrint() {
        return hasWorkTime() || isColor;
    }

    @Override
    public String toString() {
        return String.format("%s може да ги испечати сите книги во тек на своето работно време? %s\n",
                this.name,
                String.valueOf(this.canPrint()));
    }
}

class Book {
    private String name;
    private int numPages;
    private boolean isColor;

    public Book(String name, int numPages, boolean isColor) {
        this.name = name;
        this.numPages = numPages;
        this.isColor = isColor;
    }

    //Encyclopaedia Britannica - 400 - BW
    public static Book create(String line) {
        String [] parts = line.split(" - ");
        String name = parts[0];
        int pages = Integer.parseInt(parts[1]);
        boolean color = parts.length != 2;
        return new Book(name, pages, color);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public boolean isColor() {
        return isColor;
    }

    public void setColor(boolean color) {
        isColor = color;
    }


}

public class Pechatnica {
    static List<Book> books = new ArrayList<>();
    static List<Printer> printers = new ArrayList<>();
    static List<Printer> printersEnd = new ArrayList<>();


    public static List<String> readInput() {
        final Scanner scan = new Scanner(System.in);
        final List<String> items = new ArrayList<>();
        while(scan.hasNextLine()){
            items.add(scan.nextLine());
        }
        return items;
    }

    public static void processInput(List<String> lines) {
        List<String> printerList = new ArrayList<>();
        printerList.add(lines.get(0));

        List<String> bookList = new ArrayList<>();
        bookList.add(lines.get(1));

        printers = printerList.stream()
                .map(printer -> Printer.create(printer))
                .collect(Collectors.toList());

        books = bookList.stream()
                .map(book -> Book.create(book))
                .collect(Collectors.toList());

        printersEnd = printers.stream().filter(i -> i.canPrint()).collect(Collectors.toList());



    }

    public static void main(String[] args) {
        final List<String> lines = readInput();
        processInput(lines);



        //кодот е даден да ви помогне со форматирање на output, слободно сменете го по потреба
        //за секој принтер:
        System.out.printf("%s може да ги испечати сите книги во тек на своето работно време? %s\n", "", "");
        //само за принтерите што можат да ги испечатат книгите:
        System.out.printf("Минути потребни да ги испечати сите книги %.1f\n", 0.0);
        System.out.printf("Просечен број на испечатени страници во секунда %.1f\n", 0.0);
        System.out.printf("Просечен број на испечатени страници во минута %.1f\n", 0.0);
        System.out.println();

        //останатите барања ...
    }
}