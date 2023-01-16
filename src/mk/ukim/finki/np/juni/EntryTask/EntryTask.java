package mk.ukim.finki.np.juni.EntryTask;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Влезна задача:
 * текстот беше нешто да се најде максималниот број на појавувања на даден карактер
 * во дадена линија и да се испечати тој број.
 *
 * нешто отприлика вака е влезот:
 *
 * input
 * -------
 * zzzdravoo na site
 * prijaaatno
 * naprrredno programiranje
 *
 * CTRL + D za da imate output na console posle odreden input
 *
 * output
 * ----------
 * 3 (бидејќи 'z' 3 пати се појавува)
 * 3 (бидејќи 'a' 3 пати се појавува)
 * 6 (бидејќи 'r' 6 пати се појавува)
 *
 * Решение:
 */

class Line {
    Map<Character, Integer> map;

    public Line(Map<Character, Integer> map) {
        this.map = map;
    }

    public static Line create(String line) {
        Map<Character, Integer> temp = new TreeMap<>();
        for (int i = 0; i < line.length(); i++) {
            if (Character.isAlphabetic(line.charAt(i))) {
                temp.put(Character.toLowerCase(line.charAt(i)), mapCharacter(line, Character.toLowerCase(line.charAt(i))));
            }
        }
        return new Line(temp);
    }

    public static int mapCharacter(String line, char c) {
        int counter = 0;
        for (int i = 0; i < line.length(); i++) {
            if (Character.toLowerCase(line.charAt(i)) == c) counter++;
        }
        return counter;
    }

    public int countMaxChar() {
        return map.values().stream().max(Comparator.naturalOrder()).get();
    }

}

class TextProcessor {

    List<Line> lines = new ArrayList<>();

    public void process(InputStream in, PrintStream out) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        List<String> input = br.lines().collect(Collectors.toList());

        lines = input.stream()
                .map(i -> Line.create(i))
                .collect(Collectors.toList());


        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));

        lines.stream().forEach(i -> pw.println(i.countMaxChar()));

        pw.flush();

    }
}


public class EntryTask {
    public static void main(String[] args) {
        TextProcessor textProcessor = new TextProcessor();
        textProcessor.process(System.in, System.out);
    }
}