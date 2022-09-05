package mk.ukim.finki.np.auds.av7.LetterArrange;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

class LetterArranger {

    /*
    "kO pSk sO" should return: "Ok Os Skp"

    1. "kO pSk sO"
    2. "Ok" "Skp" "Os"
     */

    //kO -> Ok
    public static String arrangeWord(String word) {
        return word.chars()
                .mapToObj(i -> String.valueOf((char) i))
                .sorted()
                .collect(Collectors.joining());
    }

    public static String arrangeSentence(String sentence) {
       return Arrays.stream(sentence.split("\\s++"))
               .map(i -> arrangeWord(i))
               .sorted()
               .collect(Collectors.joining(" "));
    }
}

public class LetterArrangerTest {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String sentence = input.nextLine();

        System.out.println(LetterArranger.arrangeSentence(sentence));

    }
}
