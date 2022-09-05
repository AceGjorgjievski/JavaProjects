package mk.ukim.finki.np.auds.av5.palindrome;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LongestPalindrome {

    public static void readData(InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        List<String> result = bufferedReader.lines()
                .filter(l -> isPalindromeStream(l))
                .max(Comparator.comparing(String::length))
                .stream().collect(Collectors.toList());

        System.out.println(result);

    }

    public static boolean isPalindrome(String line){
        int length = line.length();
        for(int i=0; i<length/2; i++){
            if(line.charAt(i) != line.charAt(length -1 -i)){
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeStream(String line){
        return IntStream.range(0,line.length()/2)
                .allMatch(l -> line.charAt(l) == line.charAt(line.length() -1-l));
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Ace\\Desktop\\JavaProjects\\src\\mk\\ukim\\finki\\np\\txt\\palindromeInput.txt");
        try {
            readData(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
