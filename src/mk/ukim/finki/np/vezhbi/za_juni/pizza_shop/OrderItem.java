package mk.ukim.finki.np.vezhbi.za_juni.pizza_shop;

public class OrderItem {
    private Item item;
    private int count;

    public OrderItem(Item item, int count) throws ItemOutOfStockException {
        if(count > 10) throw new ItemOutOfStockException(count);
        this.item = item;
        this.count = count;
    }

    public int getPrice() {
        return this.item.getPrice() * this.count;
    }

    @Override
    public String toString() {
        return String.format("%s-15sx%2d%5d$",
                this.item.getType(),
                this.count,
                this.getPrice());
    }
}
