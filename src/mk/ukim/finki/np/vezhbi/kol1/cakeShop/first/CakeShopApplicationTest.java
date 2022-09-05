package mk.ukim.finki.np.vezhbi.kol1.cakeShop.first;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Item(String name) {
        this.name = name;
        this.price = 0;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

class Order implements Comparable<Order>{
    private int id;
    private List<Item> itemList;

    public Order() {
        id = -1;
        itemList = new ArrayList<>();
    }

    public Order(int id, List<Item> itemList) {
        this.id = id;
        this.itemList = itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public int getId() {
        return id;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public static Order createOrder(String line) {
        String [] parts = line.split("\\s++");
        int orderId = Integer.parseInt(parts[0]);
        List<Item> newItems = new ArrayList<>();
        Arrays.stream(parts)
                .skip(1)
                .forEach(i -> {
                    if(Character.isAlphabetic(i.charAt(0))) {
                        newItems.add(new Item(i));
                    } else {
                        newItems.get(newItems.size()-1).setPrice(Integer.parseInt(i));
                    }
                });
        return new Order(orderId,newItems);
    }

    @Override
    public int compareTo(Order other) {
        return Integer.compare(this.itemList.size(),other.itemList.size());
    }

    @Override
    public String toString() {
        return String.format("%d %d",id,getItemList().size());
    }
}

class CakeShopApplication {
    private List<Order> orderList;
    private int minOrderItems;

    public CakeShopApplication() {
        orderList = new ArrayList<>();
    }

    public CakeShopApplication(int minOrderItems) {
        this.minOrderItems = minOrderItems;
        orderList = new ArrayList<>();
    }

    public int readCakeOrders(InputStream inputStream) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        this.orderList = bf.lines()
                .map(i -> Order.createOrder(i))
                .collect(Collectors.toList());

        //for each order how many items are and then count them
        //sum raboti iskluchivo na int stream, zatoa mapToInt()
        return (int)this.orderList.stream()
                .mapToInt(i -> i.getItemList().size())
                .sum();
    }

    public void printLongestOrder(PrintStream out) {
        PrintWriter printWriter = new PrintWriter(out);
        Order longestOrder = this.orderList.stream()
                .max(Comparator.naturalOrder())
                .orElseGet(Order::new);

        printWriter.write(longestOrder.toString());
        printWriter.flush();

    }
}

public class CakeShopApplicationTest {

    /**
     * TODO: Uncomment code
     */
    public static void main(String[] args) {
        //FIRST
//        CakeShopApplication cakeShopApplication = new CakeShopApplication();
//
//        System.out.println("--- READING FROM INPUT STREAM ---");
//        System.out.println(cakeShopApplication.readCakeOrders(System.in));
//
//        System.out.println("--- PRINTING LARGEST ORDER TO OUTPUT STREAM ---");
//        cakeShopApplication.printLongestOrder(System.out);
        //SECOND
//        CakeShopApplication cakeShopApplication = new CakeShopApplication(4);
//
//        System.out.println("--- READING FROM INPUT STREAM ---");
//        cakeShopApplication.readCakeOrders(System.in);
//
//        System.out.println("--- PRINTING TO OUTPUT STREAM ---");
//        cakeShopApplication.printAllOrders(System.out);
    }
}