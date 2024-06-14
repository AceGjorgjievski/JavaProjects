package InterviewQuestions.one_inside.task_1;

import java.util.List;

public class Menu {
    private String id;
    private List<MenuItem> menuItems;

    public Menu() {
        this.id = "M-0";
        this.menuItems = List.of(
                new DessertFoodMenuItem(
                        "Dessert - 1",
                        "Dessert - 1 description",
                        15.00
                ),
                new AlcoholicBeverageMenuItem(
                        "Alcohol - 1",
                        "Alcohol - 1 description",
                        20.00),
                new NonAlcoholicBeverageMenuItem(
                        "NonAlcohol - 1",
                        "NonAlcohol - 1 description",
                        17.00)
        );
    }

    public Menu(List<MenuItem> menuItems) {
        this.id = "M-0";
        this.menuItems = menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }

    public void removeMenuItem(String menuItemName) {
        this.menuItems.removeIf(menuItem -> menuItem.getName().equals(menuItemName));
    }

    public void getInformationAboutMenuItem() {
        this.menuItems.forEach(menuItem -> System.out.println(menuItem.toString()));
    }
}
