package mk.ukim.finki.sqt.auds.aud_4_graph_coverage;

public class PatternIndex {
    public static void main(String[] argv) {
        if (argv.length != 2) {
            System.out.println
                    ("java PatternIndex Subject Pattern");
            return;
        }
        String subject = argv[0];
        String pattern = argv[1];
        int n = 0;
        if ((n = patternIndex(subject, pattern)) == -1)
            System.out.println
                    ("Pattern string is not a substring of the subject string");
        else
            System.out.println
                    ("Pattern string begins at character " + n);
    }

    /**
     * Find index of pattern in subject string
     *
     * @param subject String to search
     * @param pattern String to find
     * @return index (zero-based) of first occurrence of pattern in subject; -1 if
     * not found
     * @throws NullPointerException if subject or pattern is null
     */
    public static int patternIndex(String subject, String pattern) {//ab a
        final int NOTFOUND = -1;
        int iSub = 0, rtnIndex = NOTFOUND;
        boolean isPat = false;
        int subjectLen = subject.length();//2
        int patternLen = pattern.length();//1

        while (isPat == false && iSub + patternLen - 1 < subjectLen) {
            if (subject.charAt(iSub) == pattern.charAt(0)) {
                rtnIndex = iSub; // Starting at zero
                isPat = true;
                for (int iPat = 1; iPat < patternLen; iPat++) {
                    if (subject.charAt(iSub + iPat) != pattern.charAt(iPat)) {
                        rtnIndex = NOTFOUND;
                        isPat = false;
                        break; // out of for loop
                    }
                }
            }
            iSub++;
        }
        return (rtnIndex);
    }
}