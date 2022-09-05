package mk.ukim.finki.aps.kodovi.kod3_DP.Knapsack;



public class KnapSack {

    public static int dpKnapsack(int [] t, int [] p, int C, int n){

        int rows = t.length + 1;
        int cols = C + 1;
        int [][] result = new int[n+1][cols];

        for(int i=0; i<=n; i++){
            result[i][0] = 0;
        }

        for(int j=0; j<=C; j++){
            result[0][j] = 0;
        }

        for(int i = 1; i<=n; i++){
            for(int j=1; j<=C; j++){
                if(t[i-1] <= j)
                    result[i][j] = Math.max(result[i-1][j],result[i-1][j - t[i-1]] + p[i-1]);
                else
                    result[i][j] = result[i-1][j];
            }
        }
        return result[n][C];
    }


    public static void main(String[] args) {
    //        DP5 dp = new DP5();

        int n = 3;
        int C = 7;//50 za ova pod nego
        int [] p = new int[]{60, 100, 120};
        int [] t = new int[]{10, 20, 30};
//        int [] p = new int[]{60,28,20,24};
//        int [] t = new int[]{10,7,4,2};

        System.out.println(dpKnapsack(t, p, C,n));
    }
}

