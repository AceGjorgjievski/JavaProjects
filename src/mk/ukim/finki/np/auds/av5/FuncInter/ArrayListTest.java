package mk.ukim.finki.np.auds.av5.FuncInter;


import java.util.*;
import java.util.function.Supplier;

public class ArrayListTest {

    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<>(100);
        List<String> stringList = new ArrayList<>(100);

        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        integerList.add(2);
        integerList.add(2);
        integerList.add(5);

        stringList.add("A");
        stringList.add("B");
        stringList.add("C");
        stringList.add("D");

        System.out.println(integerList);
        System.out.println(stringList);

        System.out.println(integerList.size());
        System.out.println(stringList.size());

        System.out.println(integerList.contains(2));
        System.out.println(integerList.contains(20));
        System.out.println(stringList.contains("A"));
        System.out.println(stringList.contains("20"));


        System.out.println(integerList.indexOf(2));
        System.out.println(integerList.indexOf(20));

        System.out.println();
        System.out.println();

        System.out.println(integerList.lastIndexOf(2));
        System.out.println(integerList.lastIndexOf(20));

        System.out.println(integerList.get(4));
        System.out.println(stringList.get(2));

        System.out.println(integerList.removeIf(i -> i>3));
        System.out.println(integerList);

        Supplier<Integer> integerSupplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(100);
            }
        };



    }
}
