package mk.ukim.finki.aps.labs.lab4.XML;

import mk.ukim.finki.aps.kodovi.kod4_StackQueue.ArrayStack;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class XML {

    /*
    2
[text]
[/text]
     */
    public static boolean areSame(String left, String right){
        if(left.length()+1 != right.length()) return false;
        int j = 1;
        for(int i=2; i<left.length()-1; i++){
            if(left.charAt(j) != right.charAt(i)){
                return false;
            }
            j++;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String[] redovi = new String[n];

        for (int i = 0; i < n; i++)
            redovi[i] = br.readLine();

        int valid = 1;
        ArrayStack<String> arrayStack = new ArrayStack<>(n);
        for (int i = 0; i < redovi.length; i++) {
            if(redovi[i].charAt(0) == '[' && redovi[i].charAt(1) != '/'){
                arrayStack.push(redovi[i]);
            }
            else if(redovi[i].charAt(0) == '[' && redovi[i].charAt(1) == '/'){
                String first = arrayStack.peek();
                if(first != null) {
                    if(areSame(first,redovi[i])){
                        arrayStack.pop();
                    } else {
                        valid = 0;
                        break;
                    }
                }

            }
        }


        System.out.println(valid);

        br.close();
    }
}
