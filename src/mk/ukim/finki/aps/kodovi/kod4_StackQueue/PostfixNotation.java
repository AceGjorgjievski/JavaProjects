package mk.ukim.finki.aps.kodovi.kod4_StackQueue;

import java.util.Scanner;

public class PostfixNotation {

    /*
        12 +
     */

    public static int resultPostfix(char[] expression) {

       ArrayStack<Integer> arrayStack = new ArrayStack<>(1000);
        int num = 0;
       for(int i=0; i<expression.length; i++){
           if(Character.isDigit(expression[i]) && Character.isDigit(expression[i+1])){
               num = num*10 + Character.getNumericValue(expression[i]);
               arrayStack.push(num);
           } else if(Character.isDigit(expression[i]) && !Character.isDigit(expression[i+1])) {
               num = num * 10 + Character.getNumericValue(expression[i]);
               arrayStack.push(num);
               num = 0;
           }
           if(expression[i] == '+' || expression[i] == '-' || expression[i] == '*' || expression[i] == '/'){
               int num1 = arrayStack.pop();
               int num2 = arrayStack.pop();

               if(expression[i] == '+'){
                   arrayStack.push(num1+num2);
               } else if(expression[i] == '-'){
                   arrayStack.push(num2-num1);
               } else if(expression[i] == '*'){
                   arrayStack.push(num1*num2);
               } else if(expression[i] == '/'){
                   arrayStack.push(num2/num1);
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
        //int n = s.nextInt();
//        String read = s.nextLine();
//        int n = Integer.parseInt(read);
//        int first = 1;
//        for(int i=0; i<n; i++){
//            String s1 = s.nextLine();
//            char[] c = s1.toCharArray();
//            int result = PostfixNotation.resultPostfix(c);
//            if(first == 1){
//                System.out.println();
//                first = 0;
//            }
//            System.out.print(result + "\n");
//        }
    }

    /*
zoshto mi frla exception? na ova
za povekje chitanja odma da printa rezultat?

    //int n = s.nextInt();
    String read = s.nextLine();
    int n = Integer.parseInt(read);
    for(int i=0; i<n; i++){
       String s1 = s.nextLine();
       char[] c = s1.toCharArray();
       int result = PostfixNotation.resultPostfix(c);
       System.out.println(result);
    }

1 2 +
7 4 -
0 3 /
5 2 *
100 20 -
1 2 3 * + 5 -
28 72 * 13 + 20 67 * +
1 1 1 - - 1 + 1 +
60 80 * 20 40 * /
8 9 * 4 3 - + 2 3 6 * + -


     */
}
