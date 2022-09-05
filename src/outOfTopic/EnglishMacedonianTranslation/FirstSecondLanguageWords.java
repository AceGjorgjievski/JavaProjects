package outOfTopic.EnglishMacedonianTranslation;

import java.util.ArrayList;
import java.util.List;

public class FirstSecondLanguageWords {
    /** two lists that stores all the words from the first and second language */
    private final List<Word> firstLanguageWords;
    private final List<Word> secondLanguageWords;

    /** temporary lists for storing the words */
    public static List<Word> firstLanguageTempList = new ArrayList<>();
    public static List<Word> secondLanguageTempList = new ArrayList<>();

    /** two String properties for storing the abbreviations of the two languages */
    public static String FIRST_LANGUAGE_ABBREVIATION;
    public static String SECOND_LANGUAGE_ABBREVIATION;

    /** two locks for the first and second language */
    public static boolean FIRST_LANGUAGE_LOCK = false;
    public static boolean SECOND_LANGUAGE_LOCK = false;

    /** a simple lock, boolean value */
    public static boolean LOCK = true;

    /** default constructor, just to initialize the lists */
    public FirstSecondLanguageWords() {
        this.secondLanguageWords = new ArrayList<>();
        this.firstLanguageWords = new ArrayList<>();
    }

    /**
     * Constructor with parameters
     * @param firstLanguageWords - list of words to be set to the 'firstLanguageWords' property
     * @param secondLanguageWords - list of words to be set to the 'secondLanguageWords' property
     */
    public FirstSecondLanguageWords(List<Word> firstLanguageWords, List<Word> secondLanguageWords) {
        this.firstLanguageWords = firstLanguageWords;
        this.secondLanguageWords = secondLanguageWords;
    }

    /**
     * public static method that is used for creating a Word object by the given parameter 'code',
     * then stored in the temp lists (firstLanguageTempList and secondLanguageTempList)
     *
     * @param code - used for creating a new Word object - initially passed in the private
     *             static updateLocks(code) method
     * @return - after the file reaches 'END' string, this static method returns a new
     *          'FirstSecondLanguageWords' object with the temp lists passed as a parameters
     */
    public static FirstSecondLanguageWords create(String code) {

        updateLocks(code);

        if (FIRST_LANGUAGE_LOCK) {
            if (!code.equals(FIRST_LANGUAGE_ABBREVIATION)) {
                firstLanguageTempList.add(new Word(code));
            }
        } else if (SECOND_LANGUAGE_LOCK) {
            if (!code.equals(SECOND_LANGUAGE_ABBREVIATION)) {
                secondLanguageTempList.add(new Word(code));
            }
        } else if (code.equals("END")) {
            return new FirstSecondLanguageWords(firstLanguageTempList, secondLanguageTempList);
        }
        return null;
    }

    /**
     * private static method for updating locks, maybe there is another way of
     * doing this, but I implemented it this way
     *
     * @param code - to be passed in the private initialLocks(code) method
     */
    private static void updateLocks(String code) {

        initialLocks(code);

        if (code.equals(FIRST_LANGUAGE_ABBREVIATION)) {
            SECOND_LANGUAGE_LOCK = false;
            FIRST_LANGUAGE_LOCK = true;
        } else if (code.equals(SECOND_LANGUAGE_ABBREVIATION)) {
            SECOND_LANGUAGE_LOCK = true;
            FIRST_LANGUAGE_LOCK = false;
        } else if (code.equals("END")) {
            SECOND_LANGUAGE_LOCK = false;
            FIRST_LANGUAGE_LOCK = false;
        }
    }

    /**
     * private static method for the initial Locks, firstly checks if the
     * language is present.
     *
     * For each word passing through the static 'create(code)' method from the
     * 'readInput(inputStream)' method in the 'MakeTranslation' class, if that
     * word (code) is present in the 'languageList' List in the 'Languages' class,
     * enters that if-statement, then, initially the 'LOCK' is set to 'true', it will enter
     * the second if-statement, so it will set 'FIRST_LANGUAGE_ABBREVIATION' to the 'code' parameter and
     * the 'LOCK' is set to 'false'
     *
     * This method will be called for each word from the text file.
     * And again it will check if the language is present, but now if it is present, 'LOCK' is set to
     * 'false', so it will enter the else-statement, so it will set 'SECOND_LANGUAGE_ABBREVIATION' to the 'code'
     * parameter
     *
     * @param code used for creating a new Language object
     */
    private static void initialLocks(String code) {
        Language language = new Language(code);
        if (checkIfPresent(language)) {
            if (LOCK) {
                FIRST_LANGUAGE_ABBREVIATION = code;
                LOCK = false;
            } else {
                SECOND_LANGUAGE_ABBREVIATION = code;
            }
        }
    }

    /**
     * private static method for checking if particular language with the given code
     * is present in the 'languageList' List in the 'Languages' class.
     *
     * @param language language to be checked if it is present in
     *                 the 'languageList' List in the 'Languages' class.
     * @return boolean value - true or false
     */
    private static boolean checkIfPresent(Language language) {
        List<Language> temp = FilterAbbreviations.languages.getLanguagesList();
        for (Language l : temp) {
            if (l.getCode().equals(language.getCode())) return true;
        }
        return false;
    }

    /**
     * method for nice string representation, everything stored in 'StringBuilder'
     * @return stringBuilder containing all the data to be shown
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(FIRST_LANGUAGE_ABBREVIATION +
                        " - " + FilterAbbreviations.getLanguages().getLanguageByCode(FIRST_LANGUAGE_ABBREVIATION))
                .append("\t\t\t")
                .append(SECOND_LANGUAGE_ABBREVIATION +
                        " - " + FilterAbbreviations.getLanguages().getLanguageByCode(SECOND_LANGUAGE_ABBREVIATION))
                .append("\n");

        int j = 0;
        for (Word firstLanguageWord : firstLanguageWords) {
            sb.append("---------------------------------------------------------")
                    .append("---------------------------------------------------------")
                    .append("\n");
            while (j < secondLanguageWords.size()) {
                sb.append(firstLanguageWord)
                        .append("\t\t\t")
                        .append(secondLanguageWords.get(j))
                        .append("\n");
                break;
            }
            j++;
        }

        return sb.toString();
    }
}
