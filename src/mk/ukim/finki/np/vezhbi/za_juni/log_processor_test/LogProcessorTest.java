package mk.ukim.finki.np.vezhbi.za_juni.log_processor_test;


import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Line {
    int id;
    String text;
    int idForSorting;

    public int getIdForSorting() {
        return idForSorting;
    }

    public Line(String text, int id, int idForSorting) {
        this.id = id;
        this.text = text;
        this.idForSorting = idForSorting;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public static Line createLine(String line, int idInput) {
        String [] parts = line.split(":");
        return new Line(parts[1], Integer.parseInt(parts[0]), idInput);
    }

    @Override
    public String toString() {
        return String.format("%d:%s",id,text);
    }
}

class LogProcessor {
    final Comparator<Line> idTextComparator = Comparator.comparing(Line::getIdForSorting)
            .thenComparing(Line::getText);

    public void readLines(InputStream in, PrintStream out, int id) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        List<String> lines = br.lines().collect(Collectors.toList());

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));

        lines.stream()
                .map(i -> Line.createLine(i,id))
                .sorted(idTextComparator)
                .filter(i -> i.getId() == id)
                .forEach(i -> pw.println(i.toString()));

        pw.flush();

    }
}

public class LogProcessorTest {
    public static void main(String[] args) {
        LogProcessor lineProcessor = new LogProcessor();

        lineProcessor.readLines(System.in, System.out, 12334);
    }
}