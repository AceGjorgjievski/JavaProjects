package mk.ukim.finki.np.vezhbi.labs.picerijaLabs;

public class ExtraItem implements Item{
    private String type;

    public ExtraItem(String type) throws InvalidExtraTypeException {
        if(type.equals("Ketchup") || type.equals("Coke")) {
            this.type = type;
        } else throw new InvalidExtraTypeException(type);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getPrice() {
        if(this.type.equals("Ketchup")) return 3;
        return 5;
    }
}
