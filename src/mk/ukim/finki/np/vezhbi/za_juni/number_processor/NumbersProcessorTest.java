package mk.ukim.finki.np.vezhbi.za_juni.number_processor;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


class NumberLine {
    List<Integer> integers;

    public NumberLine(List<Integer> integers) {
        this.integers = integers;
    }

    public static NumberLine create(String line) {
        String [] parts = line.split("\\s++");
        List<Integer> temp = new ArrayList<>();
        for(String s : parts) {
            temp.add(Integer.parseInt(s));
        }
        return new NumberLine(temp);
    }

    public int getMax() {
        return integers.stream().max(Comparator.naturalOrder()).get();
    }

    public boolean checkCondition() {
        TreeMap<Integer, Long> numbers = integers.stream()
                .collect(Collectors.groupingBy(
                        i -> i,
                        TreeMap::new,
                        Collectors.counting()
                ));

        //{1:1, 2:1, 3:1, 4:3}
        int max = numbers.lastKey();
        long maxNumVal = numbers.get(max);
        long maxFreq = numbers.values().stream().max(Comparator.naturalOrder()).get();

        return maxNumVal == maxFreq;
    }
}

class NumberProcessor {
    List<NumberLine> lines;


    public void readRows(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        List<String> input = reader.lines().collect(Collectors.toList());

        lines = input.stream()
                .map(i -> NumberLine.create(i))
                .collect(Collectors.toList());
    }

    public void printMaxFromRows(PrintStream out) {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
        lines.stream().filter(i -> i.checkCondition())
                        .forEach(i -> pw.println(i.getMax()));

        pw.flush();

    }
}

public class NumbersProcessorTest {

    public static void main(String[] args) {


        NumberProcessor numberProcessor = new NumberProcessor();

        numberProcessor.readRows(System.in);

        numberProcessor.printMaxFromRows(System.out);


    }
}