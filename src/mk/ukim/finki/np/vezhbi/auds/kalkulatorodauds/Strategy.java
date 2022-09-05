package mk.ukim.finki.np.vezhbi.auds.kalkulatorodauds;

public interface Strategy {
     double calculate(double num1, double num2) throws DivisionByZeroException;
}
