package mk.ukim.finki.np.auds.av1;

import java.util.Arrays;
import java.util.stream.IntStream;



public class MatrixTest {

    //[[1,2,3,4],[5,6,7,8]]
    public static double sumOfMatrix(double [][] matrix){
        double sum = 0;
        for(int i=0;i < matrix.length; i++){//br redici 2
            for(int j=0;j<matrix[i].length; j++){//br koloni 4
                sum+=matrix[i][j];
            }
        }
        return sum;
    }
    public static double averageOfMatrix(double [][] matrix){
        double sum = sumOfMatrix(matrix);
        return (sum/(matrix.length * matrix[0].length));
    }

    //[[1,2,3,4],[5,6,7,8]] -> [10,26] -> 36
    public static double average(double [][] matrix){
        return IntStream.range(0, matrix.length)//for i=0
                .mapToDouble(i-> IntStream.range(0,matrix[0].length). // for j=0
                        mapToDouble(j-> matrix[i][j]).sum())
                .sum();
        //mapToDouble -> izlezeniot mapiran tip kje bide od tip double
    }
    //[[1,2,3,4],[5,6,7,8]] -> [1,2,3,4,5,6,7,8] // od strikto(matrica) vo niza
    public static double flatMap(double [][] matrix){
        return Arrays.stream(matrix)
                .flatMapToDouble(row->Arrays.stream(row))
                .average()
                .getAsDouble();
        //double bidejkji elementite vo novata niza kje bidat double
    }

    public static void main(String[] args) {
        double [][] matrix = {{1,2,3,4},{5,6,7,8}};
        double result1 = sumOfMatrix(matrix);
        double result2 = averageOfMatrix(matrix);
        System.out.println("Sum of Matrix " + result1);
        System.out.println("Average of Matrix " + result2);
    }
}
