package mk.ukim.finki.np.vezhbi.auds.kalkulatorodauds;

public class Subtraction implements Strategy{
    @Override
    public double calculate(double num1, double num2) {
        return num1-num2;
    }
}
