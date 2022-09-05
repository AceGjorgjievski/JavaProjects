package mk.ukim.finki.np.auds.av1;

public class StringPrefix {

    public static boolean isPrefix(String str1, String str2) {
        //return str2.startsWith(str1);

        if (str1.length() > str2.length())
            return false;
        if (str1.equals(str2))
            return true;

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String name = "Ace";
        String name2 = "aceGjorgjievski";
        boolean result = StringPrefix.isPrefix(name, name2);
        System.out.println(result);
    }
}
