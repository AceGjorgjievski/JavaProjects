package mk.ukim.finki.np.vezhbi.auds.citanjeBuffRead.lineCounter;

import java.io.*;

public class WordCountTest {

    public static void readDateWithBufferedReader(InputStream inputStream) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));

        LineCounter result = bf.lines()
                .map(l -> new LineCounter(l))
                .reduce(
                        new LineCounter(0, 0, 0),
                        (left, right) -> left.sum(right)
                );
        System.out.println(result);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\np\\vezhbi\\citanjeBuffRead\\text");



            readDateWithBufferedReader(new FileInputStream(file));


    }
}
