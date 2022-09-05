package mk.ukim.finki.np.vezhbi.labs.tlefonskiImenik;

public class InvalidNameException extends Exception{

    public boolean name;

    public InvalidNameException(String name) {
        super(String.format("This name %s is invalid", name));
    }
}
