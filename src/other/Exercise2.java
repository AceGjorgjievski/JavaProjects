package other;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'aVeryBigSum' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts LONG_INTEGER_ARRAY ar as parameter.
     */

    public static long aVeryBigSum(List<Long> ar) {
        // Write your code here
        long num = 0;
        for(int i=0; i<ar.size(); i++) {
            num += ar.get(i);
        }
        return num;

    }

    public static int diagonalDifference(List<List<Integer>> arr) {
        // Write your code here
        int sumRight = 0, sumLeft=0;
        for(int i=0; i<arr.size(); i++) {

            for(int j=0; i<arr.get(i).size(); i++) {
                arr.get(i).stream().sorted().collect(Collectors.toList());
                if(i == j) {
                    sumRight += arr.get(i).get(j);
                }

                if((i+j) == arr.get(i).size()) {
                    sumLeft += arr.get(i).get(j);
                }
            };
        }

        return Math.abs(sumLeft-sumRight);
    }

    public static void miniMaxSum(List<Integer> arr) {
        // Write your code here

        Integer max = arr.stream().max((a, b) -> a >= b ? a : b).get();
        Integer min = arr.stream().min((a, b) -> a <= b ? a : b).get();
        //Integer bla = candles.stream().max((a,b) -> a < b : b ? a)).get();

        int num = (int)arr.stream().filter(i -> i.equals(max)).count();
    }

}

public class Exercise2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Long> ar = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long result = Result.aVeryBigSum(ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}