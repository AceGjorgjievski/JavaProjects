package mk.ukim.finki.np.vezhbi.labs.picerijaLabs;

public class InvalidPizzaTypeException extends Exception{
    public InvalidPizzaTypeException(String item) {
        super(String.format("This item %s is invalid.",item));
    }
}
