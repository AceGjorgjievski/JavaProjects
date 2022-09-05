package mk.ukim.finki.np.vezhbi.za_juni.kol1.shop;



import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Item {
    private String itemName;
    private int price;

    public Item(String itemName) {
        this.itemName = itemName;
        this.price = -1;
    }

    public Item(String itemName, int price) {
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

class Order {
    private int id;
    private List<Item> items;

    public Order() {
        id = -1;
        this.items = new ArrayList<>();
    }

    public Order(int id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public static Order createOrder(String line) {
        String [] parts = line.split("\\s++");
        int orderId = Integer.parseInt(parts[0]);

        List<Item> temp = new ArrayList<>();

        Arrays.stream(parts)
                .skip(1)
                .forEach(i -> {
                    if(Character.isAlphabetic(i.charAt(0))) {
                        temp.add(new Item(i));
                    } else {
                        temp.get(temp.size()-1).setPrice(Integer.parseInt(i));
                    }
                });

        return new Order(orderId, temp);
    }

    public boolean compareOrder(Order other) {
        return this.getItems().size() > other.getItems().size();
    }

    public int getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }
}

class CakeShopApplication {
    private List<Order> orders;

    public CakeShopApplication() {
        this.orders = new ArrayList<>();
    }

    public int readCakeOrders(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        this.orders = reader.lines()
                .map(i -> Order.createOrder(i))
                .collect(Collectors.toList());

        return this.orders.stream()
                .mapToInt(i -> i.getItems().size())
                .sum();
    }

    public void printLongestOrder(PrintStream out) {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
        Order maxOrder = this.orders.stream()
                .reduce((x,y) -> x.compareOrder(y) ? x : y)
                .orElseGet(Order::new);

        pw.println(String.format("%d %d",maxOrder.getId(), maxOrder.getItems().size()));
        pw.flush();
    }
}


public class CakeShopApplicationTest {
    public static void main(String[] args) {
        CakeShopApplication cakeShopApplication = new CakeShopApplication();

        System.out.println("--- READING FROM INPUT STREAM ---");
        try {
            System.out.println(cakeShopApplication.readCakeOrders(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("--- PRINTING LARGEST ORDER TO OUTPUT STREAM ---");
        cakeShopApplication.printLongestOrder(System.out);
    }
}
