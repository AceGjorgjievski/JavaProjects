package outOfTopic.EnglishMacedonianTranslation;

public class Language implements Comparable<Language> {
    /** code, name, native name for the languages in the 'Languages' class */
    private String code;
    private String name;
    private String nativeName;

    /** constructor with one parameter, used in the 'initialLocks(code)' method
     * from the 'FirstSecondLanguageWords' class*/
    public Language(String code) {
        this.code = code.toUpperCase();
    }

    /** constructor with three parameters used to initialize all the private properties,
     *  called from 'languageList' List, from the 'Languages' class*/
    public Language(String code, String name, String nativeName) {
        this.code = code.toUpperCase();
        this.name = name;
        this.nativeName = nativeName;
    }

    /** getters for the private property - code */
    public String getCode() {
        return code;
    }

    /** getters for the private property - name */
    public String getName() {
        return name;
    }

    /** getters for the private property - nativeName
     * you can use this in the 'toString()' method in the 'FirstSecondLanguageWords' class*/
    public String getNativeName() {
        return nativeName;
    }

    /** compare method */
    @Override
    public int compareTo(Language other) {
        return this.code.compareTo(other.code);
    }
}
