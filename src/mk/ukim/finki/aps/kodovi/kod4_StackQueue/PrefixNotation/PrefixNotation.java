package mk.ukim.finki.aps.kodovi.kod4_StackQueue.PrefixNotation;

import mk.ukim.finki.aps.kodovi.kod4_StackQueue.ArrayStack;
import mk.ukim.finki.aps.kodovi.kod4_StackQueue.PostfixNotation;

import java.util.Scanner;

public class PrefixNotation {

    /*
        2 3 + 5 2 * + = 15
        + * 2 5 + 3 2 = 15

     */

    private static int solve(char [] expression){

        ArrayStack<Integer> arrayStack = new ArrayStack<>(1000);

        int num = 0;

        for(int i=expression.length -1; i>0; i--){
            if(Character.isDigit(expression[i]) && Character.isDigit(expression[i+1])){
                num = num*10 + Character.getNumericValue(expression[i]);
                arrayStack.push(num);
            }
            if(Character.isDigit(expression[i]) && !Character.isDigit(expression[i+1])){
                num = num*10 + Character.getNumericValue(expression[i]);
                arrayStack.push(num);
                num = 0;
            }
            if(expression[i] == '+' || expression[i] == '-' ||expression[i] == '*' ||expression[i] == '/'){
                int num1 = arrayStack.pop();
                int num2 = arrayStack.pop();

                if(expression[i] == '+'){
                    arrayStack.push(num1 + num2);
                } else if(expression[i] == '-'){
                    arrayStack.push(num2 - num1);
                } else if(expression[i] == '*'){
                    arrayStack.push(num1*num2);
                } else if(expression[i] == '/'){
                    arrayStack.push(num2 / num1);
                }
            }
        }

        return arrayStack.pop();
    }



    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
//        String read = s.nextLine();
//        int n = Integer.parseInt(read);
        String s1 = s.nextLine();
        char[] c = s1.toCharArray();
        int result = PostfixNotation.resultPostfix(c);
        System.out.println(result);
    }
}
