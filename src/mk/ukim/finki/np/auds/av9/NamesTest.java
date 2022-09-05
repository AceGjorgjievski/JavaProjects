package mk.ukim.finki.np.auds.av9;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class NamesTest {



    private static Map<String, Integer> create(String path) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(path);
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        return bf.lines()
                .collect(Collectors.toMap(
                        line -> line.split("\\s++")[0],
                        line -> Integer.parseInt(line.split("\\s++")[1])
                ));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Map<String,Integer> boysNames = create("C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\np\\auds\\av9\\boynames.txt");
        Map<String,Integer> girlsNames = create("C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\np\\auds\\av9\\girlsnames.txt");

        Set<String> allNames = new HashSet<>();
        allNames.addAll(boysNames.keySet());
        allNames.addAll(girlsNames.keySet());

//        System.out.println(allNames);

        Map<String,Integer> hashMap = new HashMap<>();

        allNames.stream()
                        .filter(name -> boysNames.containsKey(name) && girlsNames.containsKey(name))
                                .forEach(name -> hashMap.put(name,boysNames.get(name)+girlsNames.get(name)));

        hashMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.printf("%s: %d\n",entry.getKey(),entry.getValue()));

    }
}
