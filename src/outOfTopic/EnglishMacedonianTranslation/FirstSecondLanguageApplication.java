package outOfTopic.EnglishMacedonianTranslation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FirstSecondLanguageApplication {

    /**
     * All the words from FirstSecondLanguageWords to be stored in 'words' List
     */
    private List<FirstSecondLanguageWords> words;

    /**
     * Constructor for the 'words' List initializing to a new ArrayList
     */
    public FirstSecondLanguageApplication() {
        this.words = new ArrayList<>();
    }

    /**
     * method to read all the data by creating objects using Java streams
     *
     * @param inputStream - all the data passed through the input stream
     * @throws IOException
     */
    public void readInput(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        this.words = br.lines()
                .map(l -> FirstSecondLanguageWords.create(l))
                .collect(Collectors.toList());

    }

    /**
     * method to pass all the data through the output stream, done by Java streams
     * @param outputStream - all the data passed through the output stream
     * @throws IOException
     */
    public void sendOutput(OutputStream outputStream) throws IOException {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(outputStream));

        this.words = this.words.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        this.words.forEach(i -> pw.print(i.toString()));
        pw.flush();
    }
}
