package mk.ukim.finki.np.vezhbi.labs.picerijaLabs;

public class PizzaItem implements Item{
    private String type;

    public PizzaItem(String type) throws InvalidPizzaTypeException {
        if(type.equals("Standard") || type.equals("Pepperoni") || type.equals("Vegetarian")) {
            this.type = type;
        } else throw new InvalidPizzaTypeException(type);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getPrice() {
        if(this.type.equals("Standard")) return 10;
        else if(this.type.equals("Pepperoni")) return 12;
        else return 8;
    }
}
