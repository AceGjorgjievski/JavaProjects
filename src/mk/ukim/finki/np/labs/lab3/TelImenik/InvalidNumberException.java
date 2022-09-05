package mk.ukim.finki.np.labs.lab3.TelImenik;

public class InvalidNumberException extends Exception {
    public InvalidNumberException() {
        super(String.format("Invalid number."));
    }


}
