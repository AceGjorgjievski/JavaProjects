package mk.ukim.finki.np.auds.av4;

public class UnknownOperatorException extends Exception{

    public UnknownOperatorException(char operation) {
        super(String.format("You have entered unknown operation %c to compute the given assignment",operation));
    }
}
