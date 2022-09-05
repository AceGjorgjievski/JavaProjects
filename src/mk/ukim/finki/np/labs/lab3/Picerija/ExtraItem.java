package mk.ukim.finki.np.labs.lab3.Picerija;

public class ExtraItem implements Item {

    private String name;
    private int price;
    public ExtraItem(String name) throws InvalidExtraTypeException {
        if(name.equals("Coke") || name.equals("Ketchup")){
            this.name = name;
        } else {
            throw new InvalidExtraTypeException(name);
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
        if(name.equals("Coke")){
            return 5;
        } else if(name.equals("Ketchup")){
            return 3;
        } else return -1;
    }
}
