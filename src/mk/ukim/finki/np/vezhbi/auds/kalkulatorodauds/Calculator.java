package mk.ukim.finki.np.vezhbi.auds.kalkulatorodauds;

public class Calculator {
    private double result;
    private Strategy strategy;

    public Calculator() {
        result = 0.0;
    }

    public void init(){
        System.out.println("Calculator is on.");
        System.out.printf("result = %.2f\n",result);
    }

    public double getResult() {
        return result;
    }

    public double execute(char operator, double value) throws UnknownOperatorException, DivisionByZeroException {
        if (operator == '+') strategy = new Addition();
        else if (operator == '-') strategy = new Subtraction();
        else if (operator == '*') strategy = new Multiplication();
        else if (operator == '/') strategy = new Division();
        else throw new UnknownOperatorException(operator);

        result = strategy.calculate(result, value);
        System.out.println(String.format("result %c %.2f = %.2f", operator, value, result));
        return result;
    }
}
