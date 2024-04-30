package InterviewQuestions.preperations.np_auds.Benford_Law;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class CountNumbers {
    private int [] numberOccurrences;

    public CountNumbers() {
        this.numberOccurrences = new int[10];
    }

    public void addNumber(int number) {
        this.numberOccurrences[number]++;
    }

    private int calculateTotalSum() {
        return Arrays.stream(this.numberOccurrences).sum();
    }

    @Override
    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for(int i=1; i<this.numberOccurrences.length; i++) {
//            sb.append("%d: %d\n", i, this.numberOccurrences[i]);
//        }
//        return sb.toString();

        return IntStream.range(1, this.numberOccurrences.length)
                .mapToObj(i -> {
                    return String.format("%d: %.2f%%\n",
                            i,
                            this.numberOccurrences[i]*100.0/this.calculateTotalSum()
                    );
                })
                .collect(Collectors.joining());

    }
}

class BenfordLaw {
    private List<Integer> numbers;
    private CountNumbers countNumbers;

    public BenfordLaw() {
        this.numbers = new ArrayList<>();
        countNumbers = new CountNumbers();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void readData(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        this.numbers = br.lines()
                .filter(line -> line.length() > 0)
                .map(line -> Integer.parseInt(line))
                .map(number -> convertToFirstDigit(number))
                .collect(Collectors.toList());

    }

    private Integer convertToFirstDigit(Integer number) {
        while (number >= 10) {
            number /= 10;
        }
        return number;
    }

    public void conductExperiment() {
        this.numbers.stream().forEach(number -> countNumbers.addNumber(number));
        System.out.println(countNumbers);
    }
}

public class BenfordLawTest {
    private static String FILE_PATH = "C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\InterviewQuestions\\preperations\\np_auds\\Benford_Law\\librarybooks.txt";
    public static void main(String[] args) {
        BenfordLaw benfordLaw = new BenfordLaw();
        try {
            benfordLaw.readData(new FileInputStream(new File(FILE_PATH)));
            benfordLaw.conductExperiment();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
