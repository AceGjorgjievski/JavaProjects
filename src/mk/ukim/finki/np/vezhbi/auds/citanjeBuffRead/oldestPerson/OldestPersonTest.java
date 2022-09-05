package mk.ukim.finki.np.vezhbi.auds.citanjeBuffRead.oldestPerson;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class OldestPersonTest {

    public static List<Person> readData(InputStream inputStream) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));

        return bf.lines().map(i -> new Person(i))
                .collect(Collectors.toList());

    }


    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\np\\vezhbi\\citanjeBuffRead\\oldestPerson\\given");

        List<Person> people = readData(new FileInputStream(file));
        System.out.println(people);

        Collections.sort(people);
        System.out.println(people);

        if(people.stream().max(Comparator.naturalOrder()).isPresent())
            System.out.println(people.stream().max(Comparator.naturalOrder()).get());

//        System.out.println(people);
    }
}

