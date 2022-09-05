package mk.ukim.finki.np.vezhbi.auds.kalkulatorodauds;

public class UnknownOperatorException extends Exception{
    public UnknownOperatorException(char operator) {
        super(String.format("%c is invalid operator.\nEnter again:",operator));
    }
}
