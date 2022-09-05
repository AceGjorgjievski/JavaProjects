package mk.ukim.finki.np.labs.lab3.Picerija;

public class InvalidExtraTypeException extends Exception{
    public InvalidExtraTypeException(String message) {
        super(String.format("%s is ivalid for this Extra Item"));
    }
}
