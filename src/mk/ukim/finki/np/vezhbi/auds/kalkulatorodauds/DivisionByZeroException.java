package mk.ukim.finki.np.vezhbi.auds.kalkulatorodauds;

public class DivisionByZeroException extends Exception{
    public DivisionByZeroException() {
        super(String.format("Dividing by zero is not allowed."));
    }
}
