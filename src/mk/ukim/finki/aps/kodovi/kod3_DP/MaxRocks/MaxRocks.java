package mk.ukim.finki.aps.kodovi.kod3_DP.MaxRocks;

public class MaxRocks {


    public static int maxRocks(int n, int m, int[][] initial) {
        int[][] matrix = new int[m][n];
        int posI=0,posJ=0;

        matrix[0][0] = initial[0][0];

        for (int i = 1; i < m; i++) {
            matrix[i][0] = matrix[i - 1][0] + initial[i][0];
        }

        for (int j = 1; j < n; j++) {
            matrix[0][j] = matrix[0][j - 1] + initial[0][j];
        }

        int i,j = 0;
        for ( i = 1; i < m; i++) {
            for ( j = 1; j < n; j++) {
                matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]) + initial[i][j];
                if(j == n -1){
                    break;
                }
            }
            if(i == m-1){
                i = 0;
                j=0;
                break;
            }
        }


       // int broj = matrix[i][j];
        /*

        1    3   7
        35   40 107
        37   42 109
        47   67 113
        48   165 169

         */

        for (i = 0; i < m; i++) {
            for ( j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        return matrix[m-1][n-1];
    }

    public static void main(String[] args) {
        int n = 3;
        int m = 5;
        int[][] initial = {{1, 2, 4},
                          {34, 5, 67},
                           {2, 2, 2},
                          {10, 20, 4},
                          {1, 98, 4}};
        System.out.println(maxRocks(n, m, initial));
    }

}
