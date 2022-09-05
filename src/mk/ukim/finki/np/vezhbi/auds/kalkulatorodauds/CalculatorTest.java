package mk.ukim.finki.np.vezhbi.auds.kalkulatorodauds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalculatorTest {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            Calculator calculator = new Calculator();
            calculator.init();

            while (true) {
                String whatOperation = bf.readLine();
                if(Character.toLowerCase(whatOperation.charAt(0)) == 'r') {
                    System.out.printf("Final result = %.2f\n",calculator.getResult());
                    break;
                }
                String [] parts = whatOperation.split("\\s++");

                char operator = parts[0].charAt(0);
                double value = Double.parseDouble(parts[1]);

                try {
                    double result = calculator.execute(operator,value);
                    System.out.printf("updated result = %.2f\n", result);
                } catch (UnknownOperatorException | DivisionByZeroException e) {
                    System.out.println(e.getMessage());
                }
            }


            System.out.println("Again? (Y/N)");
            String question = bf.readLine();
            char answer = Character.toLowerCase(question.charAt(0));

            if(answer == 'n') {
                System.out.println("End of program.");
                break;
            }
        }
    }
}
