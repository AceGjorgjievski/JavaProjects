package mk.ukim.finki.aps.kodovi.kod3_DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DP12 {

    int binomial_coefficient(int n, int m) {
        int i, j;

        int bc[][] = new int[n + 1][n + 1]; // tabela so binomni koeficienti

        for (i = 0; i <= n; i++) {
            bc[i][0] = 1;
        }

        for (j = 0; j <= n; j++) {
            bc[j][j] = 1;
        }

        for (i = 1; i <= n; i++) {
            for (j = 1; j < i; j++) {
                bc[i][j] = bc[i - 1][j - 1] + bc[i - 1][j];
            }
        }

        return bc[n][m];
    }
    int a[][] = new int[100][100];
    int best[][] = new int[100][100];

    void maksimalen_zbir(int m, int n) {
        int i, j;


        // inicijalizacija na trivijalni reshenija
        best[0][0] = a[0][0];

        for (i = 1; i < m; i++) {
            best[i][0] = best[i - 1][0] + a[i][0]; // prva kolona
        }
        for (i = 1; i < n; i++) {
            best[0][i] = best[0][i - 1] + a[0][i]; // prva redica
        }
        for (i = 1; i < m; i++) {
            for (j = 1; j < n; j++) {
                best[i][j] = Math.max(best[i - 1][j], best[i][j - 1]) + a[i][j];
            }
        }

    }

    public static void main(String[] args) throws Exception {
        int i, j;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        DP12 dp = new DP12();
        
        /*
        // zadaca 1
        System.out.println(dp.binomial_coefficient(5, 2));

        // zadaca 2
        System.out.println("Vnesi broj na redici: ");
        int m = Integer.parseInt(br.readLine());
        System.out.println("Vnesi broj na koloni: ");
        int n = Integer.parseInt(br.readLine());

        for (i = 0; i < m; i++) {       // vnesuvanje na broj na kamenja vo sekoe pole
            System.out.println("Vnesi ja " +(i+1)+ " redica: ");
            for (j = 0; j < n; j++) {
                dp.a[i][j] = Integer.parseInt(br.readLine());
            }
        }

        dp.maksimalen_zbir(m, n);

        System.out.println("Maksimalniot zbir e " + dp.best[m - 1][n - 1]);
        */       
        
    }
}
