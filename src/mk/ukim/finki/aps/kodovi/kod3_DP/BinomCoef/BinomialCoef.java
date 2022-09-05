package mk.ukim.finki.aps.kodovi.kod3_DP.BinomCoef;

public class BinomialCoef {


    public static int getMaxBinCoef(int n, int k){
        int [][] matrix = new int[n+1][n+1];

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                matrix[i][0] = 1;
            }
        }

        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                matrix[i][j] = matrix[i-1][j-1] + matrix[i-1][j];
            }
        }

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        return matrix[n][n/2];
    }


    public static void main(String[] args) {
        System.out.println(getMaxBinCoef(4,2));
    }
}
