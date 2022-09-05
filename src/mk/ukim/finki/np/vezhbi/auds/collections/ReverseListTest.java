package mk.ukim.finki.np.vezhbi.auds.collections;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReverseListTest {

    public static <T> void conduct(Collection<T> collection) {
        List<T> list = new ArrayList<>(collection);
        Collections.reverse(list);
        list.stream()
                .forEach(i -> System.out.print(i + " "));
    }

    public static boolean isPrime(int number) {
        for(int i=2;i<=number/2; i++){
            if(number%i==0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> list = IntStream.range(2,101).boxed().collect(Collectors.toList());

        for(int i=2; i<list.size(); i++) {
            if(isPrime(list.get(i))){
                for(int j=i+1; j<list.size(); j++) {
                    if(list.get(j)%list.get(i) == 0) {
                        list.remove(j);
                        --j;
                    }
                }
            }
        }
        System.out.println(list);

    }
}
