package mk.ukim.finki.np.vezhbi.za_juni.line_processor;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Line {
    String wholeLine;
    int characterOccurrences;

    public Line(String wholeLine, int characterOccurrences) {
        this.wholeLine = wholeLine;
        this.characterOccurrences = characterOccurrences;
    }


}

class LineProcessor {

    public int countCharacter(String line, char c) {
        int counter = 0;
        for(int i=0; i<line.length(); i++) {
            if(Character.toLowerCase(line.charAt(i)) == Character.toLowerCase(c)) counter++;
        }
        return counter;
    }

    public void readLines(InputStream in, PrintStream out, char c) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        List<String> list = br.lines().collect(Collectors.toList());


        String maxLine = list.get(0);
        int maxOccChar = 0;


        String maxLenLine = list.get(0);

        for(String s : list) {
            if(countCharacter(s,c) >= maxOccChar) {
                maxLine = s;
                maxOccChar = countCharacter(s,c);
            }

            if(s.length() >= maxLenLine.length()) {
                maxLenLine = s;
            }
        }

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
        pw.println(maxLine);
        pw.flush();

    }
}

public class LineProcessorTest {
    public static void main(String[] args) {
        LineProcessor lineProcessor = new LineProcessor();

        try {
            lineProcessor.readLines(System.in, System.out, 'c');
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}