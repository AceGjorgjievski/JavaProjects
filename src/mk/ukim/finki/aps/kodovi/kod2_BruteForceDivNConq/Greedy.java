package mk.ukim.finki.aps.kodovi.kod2_BruteForceDivNConq;

public class Greedy {

    void sortiraj_paricki(int coins[], int n) {
        int i, j, tmp;

        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {
                if (coins[i] < coins[j]) {
                    tmp = coins[i];
                    coins[i] = coins[j];
                    coins[j] = tmp;
                }
            }
        }
    }

// coins e niza so vrednostite na parickite koi se dadeni
// n e brojot na paricki
// sum e dadenata suma
// coinsNum e niza za resenieto so brojot na paricki od sekoja golemina na paricka
    int greedyCoins(int coins[], int n, int sum, int coinsNum[]) {
        //from: 1 2 5 10 50
        sortiraj_paricki(coins, n);
        //to: 50 10 5 2 1

        int i = 0;
        int br = 0; // vkupniot broj na paricki za da se formira dadenata suma

        /*

        coinsNum[0] = 85/50 => 1
        85 -= 1*50 => 35
        br = 1;

        coinsnum[1] = 35/10 = 3
        35 -= 3*10 => 5
        br = 4;

        coinsNUm[2] = 5 / 5 =>1
        5 -= 1*5 => 0
        br = 5;

         */

        while (sum > 0) {//3
            coinsNum[i] = sum / coins[i];// 3/1 = 3, 3/2 = 1
            sum -= coinsNum[i] * coins[i];//sum -= 3 * 1;
            br += coinsNum[i]; // 1
            i++;
        }
        while (i < n) { // ostatokot e so nuli za onie vrednosti koi ne se upotrebeni
            coinsNum[i] = 0;
            i++;
        }

        return br;
    }

    void sortiraj(int p[], int t[], int n) {
        int i, j, tmpP, tmpT;
        float ratioI, ratioJ;

        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {
                ratioI = (float)p[i] / t[i];
                ratioJ = (float)p[j] / t[j];

                if(ratioI < ratioJ){
                    tmpP = p[i];
                    tmpT = t[i];

                    p[i] = p[j];
                    t[i] = t[j];
                    p[j] = tmpP;
                    t[j] = tmpT;
                }


                /*if (((float) p[i] / (float) t[i]) < ((float) p[j] / (float) t[j])) {
                    tmpP = p[i];
                    tmpT = t[i];
                    p[i] = p[j];
                    t[i] = t[j];
                    p[j] = tmpP;
                    t[j] = tmpT;
                }*/
            }
        }

    }



// p i t gi sodrzat profitot i tezinata na objektite
// C e kapacitet na paketot, x e vektor na resenieto
    float greedyFractionalKnapsack(int p[], int t[], float C, int n, float x[]) {
        // float x - od sekoj objekt po kolku del sum zel, dali cel(1), polovina(1/2), itn.
        // ako e zemen nekoj objekt so drug del, osven onie cel, polovina itn.. odime spored toa
        // kolku ni ostanuva t.e. kapacitet / tezhina;
        sortiraj(p, t, n);
        // objektite se podredeni taka da bide zadovoleno p[i]/t[i] >= p[i+1]/t[i+1]

        int i;
        float profit = 0;

        for (i = 0; i < n; i++) {
            x[i] = 0;
        }


























        for (i = 0; i < n; i++) {
            if (C > t[i]) {         // objektot go stavame celosno
                C -= t[i];
                profit += p[i];
                x[i] = 1;
            } else {                // objektot go stavame delumno
                profit += (C / (float) t[i]) * (float) p[i]; // zemi onolku kolku shto ostanuva prostor vo ranecot * tolku profit
                x[i] = (C / (float) t[i]); //
                C = 0; // vekje nema mesto, popolnet e celosno i zatoa = 0;
                break;
            }
        }

        return profit;
    }

    public static void main(String[] args) {

        Greedy g = new Greedy();

        int coins[] = new int[5];
        int n = 5;
        int sum = 85;
        int coinsNum[] = new int[5];

        coins[0] = 1;
        coins[1] = 2;
        coins[2] = 5;
        coins[3] = 10;
        coins[4] = 50;

//        coins[0] = 1;
//        coins[1] = 2;
//        coins[2] = 5;
//        coins[3] = 8;
//        coins[4] = 10;

        System.out.println("Greedy coins: " + g.greedyCoins(coins, n, sum, coinsNum));

        float C = 20;
        n = 3;
        int p[] = new int[3];
        int t[] = new int[3];
        float x[] = new float[3];

        p[0] = 25;
        p[1] = 24;
        p[2] = 15;

        t[0] = 18;
        t[1] = 15;
        t[2] = 10;

        System.out.println("Greedy fractional knapsack: " + g.greedyFractionalKnapsack(p, t, C, n, x));
        
    }
}
