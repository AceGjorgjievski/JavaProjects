package mk.ukim.finki.aps.labs.lab3.AriIzraz;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArithmeticExpression {

    /*

            ( (( ((1+5)-(2-3)) - (2-8)) + ( (1+2)-((5+6)-(3-7)) ))
    ((((1+2)+(3+4))+((5+6)+(7+8)))+(((8-7)-(6-5))-((4-3)-(2-1))))

            ( ((1+6) - (6-1)) + (2+0) )
                7   -    5    +  2
         */


    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {
        int rez = 0;

        for(int i=l; i<r; i++){
            if(c[i] == '+'){
                if(c[i-1] == ')' && c[i+1] == '('){
                    return rez+presmetaj(c,i+1,r);
                } else {
                    rez += Integer.parseInt(String.valueOf(c[i-1])) + Integer.parseInt(String.valueOf(c[i+1]));
                }
                i+=2;
            } else if(c[i] == '-'){
                if(c[i-1] == ')' && c[i+1] == '('){
                    return rez-presmetaj(c,i+1,r);
                } else {
                    rez += Integer.parseInt(String.valueOf(c[i-1])) - Integer.parseInt(String.valueOf(c[i+1]));
                }
                i+=2;
            }
        }
        return rez;
    }

     /*
                           i
            ((4-9)+((8+1)-(1+1))) = 2
               -5 + (9 - 2) = -5+7 = 2


               (((1+6)-(6-1))+(2+0))
               ((5+6)-(2+2))
                ((4-9)+((8+1)-(1+1)))

         */

    int b =  (( ((1+5)-(2-3)) - (2-8)) + ( (1+2)-((5+6)-(3-7)) ));
    int a1 =  ( ((1+6)-(6-1))+(2+5) );
    int a2 =    ((5+6)-(2+2));
    int a3 = ((4-9)+((8+1)-(1+1)));

    static int presmetaj2(char c[], int l, int r) {
        int rez = 0;

        int a =   ( ((1+6)-(6-1)) + (2+3) );
        for(int i=l; i<r; i++){
            if(c.length == 5){
                if(c[2] == '+'){
                    return (c[i-1] + c[i+1]);
                }
                return (c[i-1] - c[i+1]);
            }
            if(c[i] == '+'){
              if(c[i-2] == ')' && c[i+2] == '('){
                  return rez += presmetaj2(c,0,i-1);
              } //else if()
            }
        }
        return rez;
    }

    public static void main(String[] args) throws Exception {
        //int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char [] exp = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

           // int broj = Integer.parseInt(br.readLine());


//        for(int i=0;i<exp.length; i++){
//            System.out.print(exp[i]+" ");
//        }

        br.close();

    }



}
