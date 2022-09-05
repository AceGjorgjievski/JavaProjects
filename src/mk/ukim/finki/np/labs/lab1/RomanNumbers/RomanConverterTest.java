package mk.ukim.finki.np.labs.lab1.RomanNumbers;

import java.util.Scanner;
import java.util.stream.IntStream;

public class RomanConverterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        IntStream.range(0, n)
                .forEach(x -> System.out.println(RomanConverter.toRoman(scanner.nextInt())));
        scanner.close();
    }
}

class RomanConverter {
    private static int countDigits(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            n /= 10;
        }
        return count;
    }

    @Override
    public String toString() {
        return "";
    }

    public static String checkMs(int num){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < num; i++){
            sb.append("M");
        }
        return sb.toString();
    }

    public static String fourDigits(int num) {
        String temp = threeDigits(num % 1000);
        num /= 1000;

        String temp2 = checkMs(num);
        return num == 1 ? temp2+temp : num == 2 ? temp2+temp : num == 3 ? temp2+temp : num == 4 ? temp2+temp : num == 5 ? temp2+temp :
                num == 6 ? temp2+temp : num == 7 ? temp2+temp : num == 8 ? temp2+temp : temp2+temp;
    }

    public static String threeDigits(int num) {
        String temp = twoDigits(num % 100);
        num /= 100;
        if (num > 0 && num < 5) {
            return (num == 1 ? "C" + temp : num == 2 ? "CC" + temp : num == 3 ? "CCC" + temp : "CD" + temp);
        } else if (num > 5 && num < 9) {
            return (num == 6 ? "DC" + temp : num == 7 ? "DCC" + temp : "DCCC" + temp);
        }
        return (num == 0 ? "" + temp : num == 5 ? "D" + temp : "CM" + temp);
    }

    public static String twoDigits(int num) {
        String temp = oneDigit(num % 10);
        num /= 10;
        if (num > 0 && num < 5) {
            return num == 1 ? "X" + temp : num == 2 ? "XX" + temp : num == 3 ? "XXX" + temp : "XL" + temp;
        } else if (num > 5 && num < 9) {
            return (num == 6 ? "LX" + temp : num == 7 ? "LXX" + temp : "LXXX" + temp);
        }
        return (num == 0 ? "" + temp : num == 5 ? "L" + temp : "XC" + temp);
    }

    public static String oneDigit(int num) {
        return num == 1 ? "I" : num == 2 ? "II" : num == 3 ? "III" : num == 4 ? "IV" : num == 5 ? "V" :
                num == 6 ? "VI" : num == 7 ? "VII" : num == 8 ? "VIII" : num == 9 ? "IX" : num == 10 ? "X" : "";
    }

    public static String printNum(int cifri, int n) {
        String local = "";
        switch (cifri) {
            case 4:
                local = fourDigits(n);
                break;
            case 3:
                local = threeDigits(n);
                break;
            case 2:
                local = twoDigits(n);
                break;
            default:
                local = oneDigit(n);
        }
        return local;
    }

    public static String toRoman(int n) {
        int digitNumber = countDigits(n);
        return printNum(digitNumber, n);
    }
}