package InterviewQuestions.one_inside.task_3;

public class IntegerTest {

    public static void main(String[] args) {
        System.out.println(multiplyWithoutLoopsOrOperators(12,-3));

    }

    private static int multiplyWithoutLoopsOrOperators(int number1, int number2) {
        if(number1 == 0 || number2 == 0) return 0;

        if(number2 < 0) return -multiplyWithoutLoopsOrOperators(number1, -number2);
        return number1 + multiplyWithoutLoopsOrOperators(number1, number2 - 1);
    }
}
