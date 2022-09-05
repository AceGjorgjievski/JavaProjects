package mk.ukim.finki.aps.kodovi.kod3_DP.Planes;

public class ShortestPath {


    public static int shortestPath(int n, int [] tax, int [][] flightCost){
        int [] min = new int[n];

        min[0] = tax[0];

        for(int i=1; i<n; i++){
            min[i] = Integer.MAX_VALUE;
            for(int j=0; j<i; j++){
                min[i] = Math.min(min[i],min[j] + flightCost[j][i] + tax[i]);
                                        // minimum od pr. min[j] = 2 + cena od flC[2][5] od 2 do pet + danok vo tax[5]
                            // potoa za 3kata, go imash kako megju rezultat
            }
        }

        return min[n-1];
    }


    public static void main(String[] args) {

    }
}
