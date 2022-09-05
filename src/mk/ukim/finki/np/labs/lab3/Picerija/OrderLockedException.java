package mk.ukim.finki.np.labs.lab3.Picerija;

public class OrderLockedException extends Exception {
    public OrderLockedException() {
        super(String.format("The order is locked."));
    }
}
