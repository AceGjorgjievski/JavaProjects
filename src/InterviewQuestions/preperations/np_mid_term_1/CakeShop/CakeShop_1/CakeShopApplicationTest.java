package InterviewQuestions.preperations.np_mid_term_1.CakeShop.CakeShop_1;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


class Item {
    private String name;
    private int price;

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

class Order implements Comparable<Order>{
    private int id;
    private List<Item> items;

    public Order() {
        this.id = -1;
        this.items = new ArrayList<>();
    }

    public Order(int id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    //41 P26 215 C26 1400 P37 489 P18 243 C40 215
    public static Order createNewOrder(String line) {
        String [] parts = line.split("\\s++");
        int orderId = Integer.parseInt(parts[0]);
        List<Item> orderItems = new ArrayList<>();

        Arrays.stream(parts)
                .skip(1)
                .forEach(part -> {
                    if(Character.isAlphabetic(part.charAt(0))) {
                        orderItems.add(new Item(part));
                    } else {
                        int price = Integer.parseInt(part);
                        orderItems.get(orderItems.size() - 1).setPrice(price);
                    }
                });

        return new Order(orderId, orderItems);
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return String.format("%d %d",
                this.id,
                this.items.size());
    }

    @Override
    public int compareTo(@NotNull Order other) {
        return Integer.compare(this.items.size(), other.items.size());
    }
}

class CakeShopApplication {
    private List<Order> orders;

    public int readCakeOrders(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        this.orders = br.lines()
                .map(line -> Order.createNewOrder(line))
                .collect(Collectors.toList());

        return this.orders.stream()
                .mapToInt(order -> order.getItems().size())
                .sum();
    }

    public void printLongestOrder(PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        Comparator<Order> itemComparator = Comparator.comparingInt(order -> order.getItems().size());
        Order longestOrder = this.orders.stream()
                .max(itemComparator)
                .orElseGet(Order::new);

        pw.println(longestOrder.toString());
        pw.flush();

    }
}

public class CakeShopApplicationTest {
    public static void main(String[] args) {
        CakeShopApplication cakeShopApplication = new CakeShopApplication();

        System.out.println("--- READING FROM INPUT STREAM ---");
        System.out.println(cakeShopApplication.readCakeOrders(System.in));

        System.out.println("--- PRINTING LARGEST ORDER TO OUTPUT STREAM ---");
        cakeShopApplication.printLongestOrder(System.out);
    }
}
