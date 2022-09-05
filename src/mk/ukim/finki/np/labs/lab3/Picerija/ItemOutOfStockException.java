package mk.ukim.finki.np.labs.lab3.Picerija;

public class ItemOutOfStockException extends Throwable {
    public ItemOutOfStockException(Item item) {
        super(String.format("%s out of stock!",item));
    }
}
