package InterviewQuestions.preperations.np_mid_term_1.CakeShop.CakeShop_2;


import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class InvalidOrderException extends Exception {
    public InvalidOrderException(int orderId) {
        super(String.format("The order with id %d has less items than the minimum allowed.", orderId));
    }
}

enum Type {
    CAKE, PIE;
}

abstract class Item {
    protected String name;
    protected int price;

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

    abstract Type getType();

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

class Pie extends Item {

    public Pie(String name) {
        super(name);
    }

    public Pie(String name, int price) {
        super(name, price);
    }

    @Override
    Type getType() {
        return Type.PIE;
    }

    @Override
    public void setPrice(int price) {
        super.setPrice(price + 50);
    }
}

class Cake extends Item {

    public Cake(String name) {
        super(name);
    }

    public Cake(String name, int price) {
        super(name, price);
    }

    @Override
    Type getType() {
        return Type.CAKE;
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

    //13 C37 1106 P27 1469 P41 764 P4 394 C19 607 P38 237 P43 673 C14 362
    public static Order createNewOrder(String line, int minOrderItems) throws InvalidOrderException {
        String [] parts = line.split("\\s++");
        int orderId = Integer.parseInt(parts[0]);
        List<Item> orderItems = new ArrayList<>();

        Arrays.stream(parts)
                .skip(1)
                .forEach(part -> {
                    if(Character.isAlphabetic(part.charAt(0))) {
                        if(part.contains("C")) {
                            orderItems.add(new Cake(part));
                        } else {
                            orderItems.add(new Pie(part));
                        }
                    } else {
                        int price = Integer.parseInt(part);
                        orderItems.get(orderItems.size() - 1).setPrice(price);
                    }
                });

        if(orderItems.size() < minOrderItems) {
            throw new InvalidOrderException(orderId);
        }

        return new Order(orderId, orderItems);
    }

    public int totalSumCalculated() {
        return this.items.stream()
                .mapToInt(item -> item.getPrice())
                .sum();
    }

    public int totalPies() {
        return (int) this.items.stream()
                .filter(item -> item.getType().equals(Type.PIE))
                .count();
    }

    public int totalCakes() {
        return (int) this.items.stream()
                .filter(item -> item.getType().equals(Type.CAKE))
                .count();
    }

    @Override
    public int compareTo(@NotNull Order other) {
        return Integer.compare(this.totalSumCalculated(), other.totalSumCalculated());
    }

    //orderId totalOrderItems totalPies totalCakes totalAmount
    @Override
    public String toString() {
        return String.format("%d %d %d %d %d",
                this.id,
                this.items.size(),
                this.totalPies(),
                this.totalCakes(),
                this.totalSumCalculated());
    }
}

class CakeShopApplication {
    private int minOrderItems;
    private List<Order> orders;

    public CakeShopApplication(int minOrderItems) {
        this.minOrderItems = minOrderItems;
        this.orders = new ArrayList<>();
    }

    public void readCakeOrders(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        this.orders = br.lines()
                .map(line -> {
                    try {
                        return Order.createNewOrder(line, minOrderItems);
                    } catch (InvalidOrderException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .collect(Collectors.toList());

        this.orders = this.orders.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void printAllOrders(PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        this.orders.stream().sorted(Comparator.reverseOrder())
                .forEach(order -> pw.println(order.toString()));
        pw.flush();
    }
}

public class CakeShopApplicationTest2 {
    public static void main(String[] args) {
        CakeShopApplication cakeShopApplication = new CakeShopApplication(4);

        System.out.println("--- READING FROM INPUT STREAM ---");
        cakeShopApplication.readCakeOrders(System.in);

        System.out.println("--- PRINTING TO OUTPUT STREAM ---");
        cakeShopApplication.printAllOrders(System.out);
    }
}
