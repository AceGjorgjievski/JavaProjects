package mk.ukim.finki.aps.kodovi.kod2_BruteForceDivNConq.FractiKnapSack;

public class FractionalKnapsack {


    public static void sortiraj(int p[], int w[], int n) {

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                float ratioI = (float) p[i] / w[i];
                float ratioJ = (float) p[j] / w[j];

                if (ratioI < ratioJ) {
                    int tmp = p[i];
                    p[i] = p[j];
                    p[j] = tmp;

                    int tmw = w[i];
                    w[i] = w[j];
                    w[j] = tmw;
                }
            }
        }

    }

    public static float greedyFractionalKnapsack(int p[], int w[], float C, int n, float x[]) {

        sortiraj(p, w, n);

        //c = 20;
        //n = 3

        //C=7
        //n = 3
        //12,6,5,4(p/w)



        float profit = 0;

        for (int i = 0; i < n; i++) {
            x[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            if (C > w[i]) {
                C -= w[i];//5
                profit += p[i];//24
                x[i] = 1;
            } else {
                // C < w[i]
                //c = 10, w[i] = 20
                profit += (C / (float) w[i]) * p[i];
                x[i] = (C / (float) w[i]);
                C = 0;
                //C -= (C / (float)w[i]); << i ova e isto namesto C = 0;
                break;
            }
        }
        return profit;
    }

    public static void main(String[] args) {

        FractionalKnapsack fractionalKnapsack = new FractionalKnapsack();

        int coins[] = new int[5];
        int n = 5;
        int sum = 79;
        int coinsNum[] = new int[5];

        coins[0] = 1;
        coins[1] = 2;
        coins[2] = 5;
        coins[3] = 10;
        coins[4] = 50;

        //System.out.println("Greedy coins: " + g.greedyCoins(coins, n, sum, coinsNum));

        float C = 7;
        n = 4;
        //int p[] = new int[3];
        //int t[] = new int[3];
        float x[] = new float[4];

//        int p[] = {25, 24, 15};
//        int w[] = {18, 15, 10};

        int [] p = {60,28,20,24};
        int [] w = {10,7,4,2};


        System.out.println("Greedy fractional knapsack     : " + greedyFractionalKnapsack(p, w, C, n, x));

    }
}


