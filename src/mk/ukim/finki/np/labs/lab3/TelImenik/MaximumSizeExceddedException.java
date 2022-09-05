package mk.ukim.finki.np.labs.lab3.TelImenik;

public class MaximumSizeExceddedException extends Exception {

    public MaximumSizeExceddedException() {
        super(String.format("Invalid contact number"));
    }
}
