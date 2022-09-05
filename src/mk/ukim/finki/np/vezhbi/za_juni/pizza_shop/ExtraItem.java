package mk.ukim.finki.np.vezhbi.za_juni.pizza_shop;

public class ExtraItem implements Item {
    private String type;
    private MenuExtraItems extraItems;
    private int ExtraItemCoke;
    private int ExtraItemKetchup;

    public ExtraItem(String type) throws InvalidExtraTypeException {
        if(type.equals("Coke") || type.equals("Ketchup")) {
            this.type = type;
            this.setExtraItems(type);
        } else {
            throw new InvalidExtraTypeException(type);
        }
    }

    private void setExtraItems(String type) {
        if(type.equals("Coke")) {
            this.ExtraItemCoke++;
            this.extraItems = extraItems.COKE;
        } else {
            this.ExtraItemKetchup++;
            this.extraItems = extraItems.KETCHUP;
        }
    }

    public int getExtraItemCoke() {
        return ExtraItemCoke;
    }

    public int getExtraItemKetchup() {
        return ExtraItemKetchup;
    }

    public String getOrderedItem() {
        return extraItems.toString();
    }

    @Override
    public int getPrice() {
        return this.type.equals("Ketchup") ? 3 : 5;
    }

    @Override
    public ItemType getType() {
        return ItemType.EXTRA;
    }
}
