package mk.ukim.finki.vezhbi.napredno.youtube;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) throws IOException {

        List<Integer> integerList = List.of(1,2,3,4,5);

        List<String> stringList = List.of("ace","petar","kiko","stefanie","nikolina","ana","tete");

//        integerList.stream()
//                .mapToInt(i -> i)
//                .average()
//                .ifPresent(System.out::println);

//        stringList.stream()
//                .sorted()
//                .map(String::toUpperCase)
//                .filter(x -> x.contains("TE"))
//                .forEach(System.out::println);

        File file = new File("C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\vezhbi\\napredno\\dat.txt");

        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        bf.lines()
                        .map(i -> i.split("\\s++"))
                                        .filter(i -> Integer.parseInt(i[1]) > 18)
                .map(i -> i[0].toUpperCase())
                //.forEach(i -> System.out.println(String.format("%s %d %s",i[0],Integer.parseInt(i[1]),i[2])));
                        .forEach(System.out::println);

        bf.close();

    }
}

