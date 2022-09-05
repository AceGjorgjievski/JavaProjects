package mk.ukim.finki.np.vezhbi.labs.picerijaLabs;

public class ItemOutOfStockException extends Exception{
    public ItemOutOfStockException(Item item) {
        super(String.format("The following item %s is oout of stock",item.toString()));
    }
}
