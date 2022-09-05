package mk.ukim.finki.aps.kodovi.kod3_DP;

public class DP4 {

    public static void main(String[] args) throws Exception {
        int i, j = 0;

        System.out.println("Paricki so dinamicko programiranje");

        int COINS = 5;
        int SUMS = 100;

        // niza za vrednostite na parickite
        int coins[] = new int[]{1, 2, 5, 8, 10};

        // niza za brojot na paricki potrebni za sekoja suma pomala od SUMS
        int coinsNum[] = new int[SUMS + 1];

        for (i = 0; i <= SUMS; i++) {
            coinsNum[i] = 0;                     // inicijalizacija za pocetniot broj na paricki
        }
        // pocetni uslovi: ako sumata e 1, 2, 5, 8 ili 10, togas optimalnoto resenieto e 1
        for (i = 0; i < COINS; i++) {
            coinsNum[coins[i]] = 1;
        }

        for (i = 0; i < SUMS; i++) {

            if (coinsNum[i] == 0) {
                continue;                               // da se preskoknat  nevozmoznite sumi
            }
            for (j = 0; j < COINS; ++j) {
                // najdi gi optimalnite resenija za sumite koi mozat da
                // se napravat so dodavanje na edna paricka na tekovnata suma
                if (i + coins[j] <= SUMS) {
                    if ((coinsNum[i + coins[j]] == 0) || (coinsNum[i + coins[j]] > coinsNum[i] + 1)) {
                        coinsNum[i + coins[j]] = coinsNum[i] + 1;
                    }
                }
            }
        }

        System.out.println(coinsNum[23]);

    }
}
