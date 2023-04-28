package mk.ukim.finki.sqt.labs.lab_2;

// Introduction to Software Testing
// Authors: Paul Ammann & Jeff Offutt
// Chapter 7; page ??
// Can be run from command line
// See FmtRewrap.num for a numbered version.
// No JUnit tests at this time.

/**
 * ****************************************************
 * Rewraps the string (Similar to the Unix fmt).
 * Given a string S, eliminate existing CRs and add CRs to the
 * closest spaces before column N.  Two CRs in a row are considered to
 * be "hard CRs" and are left alone.
 * ********************************************************
 */

import java.io.*;
import java.util.Scanner;

public class FmtRewrap {
    static final char CR = '\n';
    static final int inWord = 0;
    static final int betweenWord = 1;
    static final int lineBreak = 2;
    static final int crFound = 3;

    static private String fmtRewrap(String S, int N) {
        int state = betweenWord;
        int lastSpace = -1;
        int col = 1;
        int i = 0;
        char c;

        char SArr[] = S.toCharArray();
        while (i < S.length()) { //4
            c = SArr[i];
            col++; //5
            if (col >= N) //6
                state = lineBreak;
            else if (c == CR) //7
                state = crFound;
            else if (c == ' ')//8
                state = betweenWord;
            else //9
                state = inWord;
            switch (state) {//10
                case betweenWord://11
                    lastSpace = i;
                    break;

                case lineBreak://12
                    SArr[lastSpace] = CR;
                    col = i - lastSpace;
                    break;

                case crFound://13
                    if (i + 1 < S.length() && SArr[i + 1] == CR) {//14
                        i++; // Two CRs => hard return
                        col = 1;
                    } else //15
                        SArr[i] = ' ';
                    break;//16

                case inWord://17
                default://18
                    break;
            }  // end switch
            i++;//19
        }  // end while
        S = new String(SArr) + CR;//20
        return (S);
    }

    public static void main(String[] argv) {  // Driver method for fmtRewrap
        // Read an array and an integer from standard input, call fmtRewrap()
        int integer = 0;
        int[] inArr = new int[argv.length];

        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s++");

//        if (argv.length != 1)
//        {
//            System.out.println ("Usage: java FmtRewrap v1 ");
//            return;
//        }

        System.out.println("Enter an integer: ");
        integer = getN();


//        System.out.println ("The Result String is: " + fmtRewrap (argv[0], integer));
        System.out.println("The Result String is: " + fmtRewrap(strings[0], integer));
    }

    // ====================================
    // Read (or choose) an integer
    private static int getN() {
        int inputInt = 1;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String inStr;

        try {
            inStr = in.readLine();
            inputInt = Integer.parseInt(inStr);
        } catch (IOException e) {
            System.out.println("Could not read input, choosing 1.");
        } catch (NumberFormatException e) {
            System.out.println("Entry must be a number, choosing 1.");
        }

        return (inputInt);
    }  // end getN
}