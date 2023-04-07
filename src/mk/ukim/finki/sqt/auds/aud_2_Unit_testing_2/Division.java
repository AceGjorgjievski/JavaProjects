package mk.ukim.finki.sqt.auds.aud_2_Unit_testing_2;


public class Division {
    public int divide(int a, int b){
        if(b == 0) {
            throw new ArithmeticException("Division by zero!");
        }
        return a/b;
    }
}
