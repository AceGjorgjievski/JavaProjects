package InterviewQuestions.preperations.np_auds.Count_occurrence;

import mk.ukim.finki.os.labs.lab_2.task2.CountLetter;
import org.eclipse.jetty.server.RequestLog;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CountOccurrenceTest {
    public static int count(Collection<Collection<Collection<String>>> c, String str) {
        return (int) c.stream()
                .flatMap(col -> col.stream())
                .flatMap(col2 -> col2.stream())
                .filter(string -> string.equals(str))
                .count();
    }
    public static void main(String[] args) {
        Collection<String> col1 = Arrays.asList("apple", "banana", "apple", "orange");
        Collection<Collection<String>> collections1 = Collections.singleton(col1);
        Collection<Collection<Collection<String>>> colls2 = Collections.singleton(collections1);
        int result1 = CountOccurrenceTest.count(colls2, "apple");
        System.out.println(result1);

        List<Integer> numbers = IntStream.range(2, 101).boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        numbers.forEach(System.out::println);
    }
}
