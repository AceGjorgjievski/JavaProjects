package mk.ukim.finki.np.labs.lab3.Picerija;

public class InvalidPizzaTypeException extends Exception {
    public InvalidPizzaTypeException(String message) {
        super(String.format("%s is invalid for this Pizza Item."));
    }
}
