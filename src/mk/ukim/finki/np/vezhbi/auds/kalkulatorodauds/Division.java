package mk.ukim.finki.np.vezhbi.auds.kalkulatorodauds;

public class Division implements Strategy{
    @Override
    public double calculate(double num1, double num2) throws DivisionByZeroException {
        if (num2 != 0) return (num1/num2);
        else throw new DivisionByZeroException();
    }
}
