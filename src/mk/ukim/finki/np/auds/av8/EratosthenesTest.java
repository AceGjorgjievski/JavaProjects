package mk.ukim.finki.np.auds.av8;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EratosthenesTest {

    public static boolean isPrime(int num){
        for(int i=2; i<=num/2; i++){
            if(num%i == 0){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        // 2 3 4 5 6 7
        // 2 3 5 7

        ArrayList<Integer> list = IntStream.range(2,101)
                .boxed()
                .collect(Collectors.toCollection(() -> new ArrayList<>()));


        System.out.println(list);
        for(int i=0; i< list.size()-1; i++){
            if(isPrime(list.get(i))){
                for(int j=i+1; j< list.size(); j++){
                    if(list.get(j)%list.get(i) == 0){
                        list.remove(j);
                        j--;
                    }
                }
            }
        }
        System.out.println(list);
    }
}
