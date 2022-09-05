package mk.ukim.finki.np.vezhbi.za_juni.pizza_shop;

public class PizzaItem implements Item{
    private String type;
    private MenuPizzaItems menuPizzaItems;
    private int PizzaItemStandard;
    private int PizzaItemPepperoni;
    private int PizzaItemVegetarian;

    public PizzaItem(String type) throws InvalidPizzaTypeException {
        if(type.equals("Standard") || type.equals("Pepperoni") || type.equals("Vegetarian")) {
            this.type = type;
            this.setPizzaItems(type);
        } else {
            throw new InvalidPizzaTypeException(type);
        }
    }

    @Override
    public int getPrice() {
        return this.type.equals("Standard") ? 10 : this.type.equals("Pepperoni") ? 12 : 8;
    }

    public String getOrderedItem() {
        return menuPizzaItems.toString();
    }

    private void setPizzaItems(String type) {
        if(type.equals("Standard")) {
            this.PizzaItemStandard++;
            this.menuPizzaItems = MenuPizzaItems.STANDARD;
        }
        else if(type.equals("Pepperoni")) {
            this.PizzaItemPepperoni++;
            this.menuPizzaItems = MenuPizzaItems.PEPPERONI;
        }
        else {
            this.PizzaItemVegetarian++;
            this.menuPizzaItems = MenuPizzaItems.VEGETARIAN;
        }
    }

    public int getPizzaItemStandard() {
        return PizzaItemStandard;
    }

    public int getPizzaItemPepperoni() {
        return PizzaItemPepperoni;
    }

    public int getPizzaItemVegetarian() {
        return PizzaItemVegetarian;
    }

    @Override
    public ItemType getType() {
        return ItemType.PIZZA;
    }
}
