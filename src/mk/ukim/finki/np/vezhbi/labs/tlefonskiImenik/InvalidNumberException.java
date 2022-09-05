package mk.ukim.finki.np.vezhbi.labs.tlefonskiImenik;

public class InvalidNumberException extends Exception{

    public InvalidNumberException(String phoneNumber) {
        super(String.format("This phoneNumber %s is invalid", phoneNumber));
    }
}
