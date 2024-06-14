package InterviewQuestions.one_inside.task_1;

import java.time.Year;
import java.util.Date;
import java.util.List;

public class RestaurantTest {

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant("Restaurant 1", "address no.1");

        MenuItem menuItem1 = new MainFoodMenuItem("pizza","good pizza", 17);
        MenuItem menuItem2 = new StarterFoodMenuItem("salad","good salad", 25);
        MenuItem menuItem3 = new DessertFoodMenuItem("cake","good cake", 30);

        MenuItemQuantity menuItemQuantity1 = new MenuItemQuantity(menuItem1, 2);
        MenuItemQuantity menuItemQuantity2 = new MenuItemQuantity(menuItem2, 1);
        MenuItemQuantity menuItemQuantity3 = new MenuItemQuantity(menuItem3, 1);

        List<MenuItemQuantity> menuItemList1 = List.of(menuItemQuantity1, menuItemQuantity2, menuItemQuantity3);

        Order order1 = new Order(
                "O-1",
                new Date(
                        Integer.parseInt(Year.now().toString()),
                        6,28,17, 45),
                menuItemList1,
                5
        );

        restaurant.addOrder(order1);

        MenuItem menuItem4 = new AlcoholicBeverageMenuItem("gin","good gin", 35);
        MenuItem menuItem5 = new NonAlcoholicBeverageMenuItem("lemonade","good lemonade", 15);

        MenuItemQuantity menuItemQuantity4 = new MenuItemQuantity(menuItem4, 4);
        MenuItemQuantity menuItemQuantity5 = new MenuItemQuantity(menuItem5, 2);

        List<MenuItemQuantity> menuItemList2 = List.of(menuItemQuantity4, menuItemQuantity5);


        Order order2 = new Order(
                "O-2",
                new Date(
                        Integer.parseInt(Year.now().toString()),
                        6,28,19, 45),
                menuItemList2,
                15
        );

        restaurant.addOrder(order2);

        System.out.println(String.format("Total balance for order: %.2f", restaurant.totalBalanceForAnOrder()));
        System.out.println(String.format("Total balance for order: %.2f", restaurant.totalEarningsFromAnOrder()));

        restaurant.getAllOrders();


        restaurant.removeOrder("O-1");

        System.out.println("===== AFTER REMOVING ORDER 0-1 ======");

        restaurant.getAllOrders();

        System.out.println("Total balance after");


    }
}
