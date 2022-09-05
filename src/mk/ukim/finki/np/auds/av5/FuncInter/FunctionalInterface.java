package mk.ukim.finki.np.auds.av5.FuncInter;

import java.util.Random;
import java.util.function.*;

public class FunctionalInterface {


    public static void main(String[] args) {
        Predicate<Integer> LessThan100 = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer < 100;
            }
        };

        Predicate<Integer> lessThan100 = integer -> integer < 100;

        Supplier<Integer> integerSupplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(100);
            }
        };

        Supplier<Integer> supplier = () -> new Random().nextInt(100);


        Consumer<String> stringConsumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        Consumer<String> stringConsumer1 = s -> System.out.println(s);

        Function<Integer, String> PrintNum = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return String.format("%d",integer);
            }
        };
        //zemam neshto, vrakjam drugo kako linearna ravenka so edna nepoznata
        Function<Integer, String> printNum = integer -> String.format("%d",integer);

        BiFunction<Integer,Integer, String> sumNumbersAndFormat = new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) {
                return String.format("%d + %d = %d",integer,integer2, integer+integer2);
            }
        };

        BiFunction<Integer,Integer, String> SumNumbersAndFormat = (i, i2) -> String.format("%d + %d = %d",i,i2, i+i2);

    }
}
