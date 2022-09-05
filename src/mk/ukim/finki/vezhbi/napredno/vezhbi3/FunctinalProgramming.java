package mk.ukim.finki.vezhbi.napredno.vezhbi3;

import java.util.Arrays;
import java.util.List;

public class FunctinalProgramming {

    public static void main(String[] args) {
//        Predicate<String> predicate = s -> s.length()>3;
//        Predicate<String> predicate2 = s -> s.length()<10;
//        System.out.println(predicate.or(predicate2).testCases("acehsdgfjsdgfjsdgfs"));
//
//        Predicate<Boolean> nonNull = Objects::nonNull;
//        Predicate<Boolean> isNull = Objects::isNull;

//        Function<String, Integer> toInteger = Integer::valueOf;
//        Function<String, String> backToString = toInteger.andThen(String::valueOf);
//
//        System.out.printf("%s",toInteger.apply("123"));
//
//        Consumer<Person> greeter = p -> System.out.println("Hello, "+ p.name);
//        greeter.accept(new Person("Ace","Gjor",20));

        List<String> myList = Arrays.asList("a1","a2","a3","c4","c5");
        myList.stream()
                .filter(i -> i.startsWith("a"))
                .map(i -> i.toUpperCase())
                .sorted()
                .forEach(System.out::println);



    }
}
