package outOfTopic.EnglishMacedonianTranslation;

import java.util.ArrayList;
import java.util.List;

public class Word {
    /**
     * word - String property
     */
    private String word;

    /**
     * Constructor
     * @param word - item to be set to the 'word' property, but initially filtered
     */
    public Word(String word) {
        this.filterWord(word);
    }

    /**
     * method that filters a String in a way that checks if the ASCII (https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html)
     * values are within the alphabetic bounds (check the link if not clear), and if the characters in the String are
     * within the bounds, then add them to the 'temp' List. After that, set the 'word' property to point where the 'temp' List
     * is pointing.
     * @param word - String to be filtered
     */
    private void filterWord(String word) {
        List<Character> temp = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            if ((word.charAt(i) >= 65 && word.charAt(i) <= 90) ||
                    (word.charAt(i) >= 97 && word.charAt(i) <= 122)) {
                temp.add(word.charAt(i));
            }
        }
        this.word = word;
    }

    /** getter for the 'word' property*/
    public String getWord() {
        return word;
    }

    /**to String method, that just returns the 'word' property*/
    @Override
    public String toString() {
        return this.word;
    }
}
