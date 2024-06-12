package InterviewQuestions.Netcetera;

import java.io.*;
import java.util.*;

public class NetceteraInterview {

    private static Map<Character, Integer> map = new HashMap<>();
    private static int countOccurrences = 1;

    private static void solutionTwoForLoops(String inputLine) {
        System.out.println("Solution With Two For Loops: ");

        for(int i=0; i<inputLine.length()-1; i++) {
            for(int j=i+1; j<inputLine.length(); j++) {

                if(inputLine.charAt(i) != inputLine.charAt(j)) break;
                else countOccurrences++;

            }

            map.put(inputLine.charAt(i), countOccurrences);
            i += countOccurrences-1;
            countOccurrences = 1;
        }
    }

    private static void solutionOneForLoop(String inputLine) {
        System.out.println("Solution With One For Loop: ");

        for(int i = 0, j = i + 1; i < inputLine.length();) {
            //aaabbcdd
            if (j < inputLine.length() &&
                    inputLine.charAt(i) != inputLine.charAt(j)) {

                map.put(inputLine.charAt(i), countOccurrences);
                countOccurrences = 1;

                i = j;
                j = i + 1;

            } else if (j < inputLine.length() &&
                    inputLine.charAt(i) == inputLine.charAt(j)) {

                //aaa ->
                //i j
                countOccurrences++;
                j++;
            } else {
                map.put(inputLine.charAt(i), countOccurrences);
                countOccurrences = 1;
                break;
            }
        }
    }


    private static void processInput(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String input = br.readLine();

        // Only use one of these methods to get proper answer

//        solutionTwoForLoops(input);
        solutionOneForLoop(input);
    }

    private static void processOutput(OutputStream os) throws IOException {
        //PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
        BufferedWriter bw = null;
        StringBuilder sb = null;

        try {
            bw = new BufferedWriter(new OutputStreamWriter(os));
            sb = new StringBuilder();

            for(Map.Entry<Character, Integer> entry : map.entrySet()) {
                sb.append(entry.getValue()).append(entry.getKey());
            }
            bw.write(sb.toString());

        } catch (Exception e) {
            System.err.println("Error in process output");
        } finally {
            if(bw != null) {
                bw.flush();
                bw.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        processInput(System.in);
        processOutput(System.out);

    }
}
