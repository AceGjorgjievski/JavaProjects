package mk.ukim.finki.np.labs.lab3.Picerija;

public class PizzaItem implements Item {
    private String name;
    private int price;

    public PizzaItem(String name) throws InvalidPizzaTypeException {
        if(name.equals("Standard") || name.equals("Pepperoni") || name.equals("Vegetarian")){
            this.name = name;
        } else {
            throw new InvalidPizzaTypeException(name);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        if(name.equals("Standard")){
            return 10;
        } else if(name.equals("Pepperoni")){
            return 12;
        } else if(name.equals("Vegetarian")){
            return 8;
        } else return -1;
    }
}
