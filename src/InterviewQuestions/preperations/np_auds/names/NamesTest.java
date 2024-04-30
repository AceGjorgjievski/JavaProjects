package InterviewQuestions.preperations.np_auds.names;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class NamesTest {
    private static String BOYS_FILE = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\InterviewQuestions\\preperations\\np_auds\\names\\boynames.txt";
    private static String GIRLS_FILE = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\InterviewQuestions\\preperations\\np_auds\\names\\girlsnames.txt";

    public static Map<String, Integer> readData(String path) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        Map<String, Integer> result = new HashMap<>();
        return br.lines()
                .map(line -> {
                    String[] parts = line.split("\\s++");
                    String name = parts[0];
                    Integer freq = Integer.parseInt(parts[1]);
                    return Map.entry(name, freq);
                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static void conduct(Map<String, Integer> boyNames, Map<String, Integer> girlNames) {
        Set<String> set = new HashSet<>();

        set.addAll(boyNames.keySet());
        set.addAll(girlNames.keySet());


        Set<String> collect = set.stream()
                .filter(name -> boyNames.containsKey(name) && girlNames.containsKey(name))
                .collect(Collectors.toSet());

        Map<String, Integer> unisexNames = new HashMap<>();

        collect.stream()
                .forEach(name -> {
                    unisexNames.put(name, boyNames.get(name) + girlNames.get(name));
                    System.out.printf("%s -> Boy:%d - Girl:%d ::: Total: %d \n",
                            name,
                            boyNames.get(name),
                            girlNames.get(name),
                            boyNames.get(name) + girlNames.get(name)
                    );
                });

        unisexNames.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.printf("%s: %d\n", entry.getKey(), entry.getValue()));
//        System.out.println(unisexNames);

    }

    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Integer> boyNames = readData(BOYS_FILE);
        Map<String, Integer> girlNames = readData(GIRLS_FILE);

        conduct(boyNames, girlNames);
    }
}
