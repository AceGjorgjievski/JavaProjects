package mk.ukim.finki.np.auds.av4;


public class Calculator {
    private double result;
    private Strategy strategy;

    public Calculator() {
        this.result = 0;
    }

    public double getResult() {
        return result;
    }

    public String init() {
        return String.format("result = %.2f", result);
    }

    public String execute(double value, char operation) throws UnknownOperatorException {
        if (operation == '+') {
            strategy = new Addition();
        } else if (operation == '-') {
            strategy = new Subtraction();
        } else if (operation == '*') {
            strategy = new Multiplication();
        } else if (operation == '/') {
            strategy = new Division();
        } else {
            throw new UnsupportedOperationException(String.valueOf(operation));
        }
        result = strategy.compute(result, value);
        return String.format("result %c %.2f = %.2f", operation, value, result);
    }

    @Override
    public String toString() {
        return String.format("result %.2f", result);
    }
}
