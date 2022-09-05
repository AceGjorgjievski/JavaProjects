package other.pak2;


import java.util.Arrays;
import java.util.stream.IntStream;

public class Shopping {

     /*

        Test Case 6:
          getMin({2,3,1,1,9,7}) = 20
          16+3+1; = 20;
    */

    public static int findMaxInteger(int [] array){
        return Arrays.stream(array)
                .max()
                .getAsInt();
    }

    public static int sumIntArray(int [] array){
        return Arrays.stream(array).sum();
    }


    public static int getMin(int [] prices){
        if(prices.length == 1 ||
            prices.length == 2) return sumIntArray(prices);



        return 0;
    }

    public static void main(String[] args) {

        int[] integerArray = {2,3};
        System.out.println(getMin(integerArray));
    }
}
