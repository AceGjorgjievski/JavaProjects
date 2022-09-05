package mk.ukim.finki.np.vezhbi.labs.picerijaLabs;

public class InvalidExtraTypeException extends Exception{
    public InvalidExtraTypeException(String item) {
        super(String.format("The following item %s is not valid",item));
    }
}
