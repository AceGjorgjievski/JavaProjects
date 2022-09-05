package mk.ukim.finki.np.auds.av8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ReversePrintTest {

    public static <T> void reversePrint(Collection<T> collection){
        List<T> list = new ArrayList<>();
        list.addAll(collection);
        for(int i = collection.size()-1; i>=0; i--){
            System.out.print(list.get(i) + " ");
        }
    }

    public static <T> void reversePrint2(Collection<T> collection){
        List<T> list = new ArrayList<>(collection);
        Collections.reverse(list);
        Collections.shuffle(list);

        list.stream()
                .forEach(i -> list.toString());
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,3,4,5,6);
        reversePrint(list);
    }
}
