package mk.ukim.finki.aps.kodovi.kod3_DP.Coins;

public class Coins {

    public static int MAX_SUM = 1000;

    public static int[] minCoinsForSum(int n, int [] coins){
        int [] numOfCoinsForSum = new int[MAX_SUM+1];

        for(int i=0; i<=MAX_SUM; i++){
            numOfCoinsForSum[i] = 0;
        }
        for(int i=0; i<n; i++){
            numOfCoinsForSum[coins[i]] = 1;
        }

        for(int i=0; i<MAX_SUM; i++){
            for(int j=0; j<n; j++){
                if(i + coins[j] < MAX_SUM){
                    if(numOfCoinsForSum[i+coins[j]] == 0 ||
                        numOfCoinsForSum[i+coins[j]] > numOfCoinsForSum[i] + 1
                    ){
                        numOfCoinsForSum[i+coins[j]] = numOfCoinsForSum[i] + 1;
                    }
                }
            }
        }

        return numOfCoinsForSum;
    }

    public static void main(String[] args) {
        int n = 5;
        int [] coins = {1,2,5,8,10};

        System.out.println(minCoinsForSum(n,coins)[13]);
    }
}
