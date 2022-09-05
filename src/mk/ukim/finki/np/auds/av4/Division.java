package mk.ukim.finki.np.auds.av4;

public class Division implements Strategy {
    @Override
    public double compute(double num1, double num2) {
        if(num2 != 0){
            return num1 / num2;
        }
        return num2;
    }
}
