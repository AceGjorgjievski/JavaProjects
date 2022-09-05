package mk.ukim.finki.np.auds.av4;

public class Multiplication implements Strategy {
    @Override
    public double compute(double num1, double num2) {
        return num1*num2;
    }
}
