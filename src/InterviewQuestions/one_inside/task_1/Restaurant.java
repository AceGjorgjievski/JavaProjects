package InterviewQuestions.one_inside.task_1;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String address;
    private Menu menu;
    private List<Order> processedOrders;
    private double totalEarnings;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        this.processedOrders = new ArrayList<>();
        this.menu = new Menu();
        this.totalEarnings = 0;
    }

    public Restaurant(String name, String address, Menu menu, List<Order> processedOrders, double totalEarnings) {
        this.name = name;
        this.address = address;
        this.menu = menu;
        this.processedOrders = processedOrders;
        this.totalEarnings = totalEarnings;
    }

    public void addOrder(Order newOrder) {
        this.processedOrders.add(newOrder);
    }

    public void removeOrder(String orderId) {
        this.processedOrders.removeIf(order -> order.getId().equals(orderId));
    }

    public void getInformationAboutAnOrder() {
        this.processedOrders.forEach(order -> System.out.println(order.toString()));
    }

    public double totalBalanceForAnOrder() {
        return this.processedOrders.stream()
                .mapToDouble(Order::getTotalBalance)
                .sum();
    }

    public double totalEarningsFromAnOrder() {
        return this.processedOrders.stream()
                .mapToDouble(Order::getTotalEarning)
                .sum();
    }

    public void getInformationAboutOrder(String orderId) {
        Order foundOrder = this.processedOrders.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .get();

        System.out.println(foundOrder.toString());
    }

    public void getAllOrders() {
        if(this.processedOrders.size() > 0) {
            this.processedOrders.stream().forEach(order -> System.out.println(order.toString()));
        } else {
            System.out.println("There are not orders");
        }
    }
}
