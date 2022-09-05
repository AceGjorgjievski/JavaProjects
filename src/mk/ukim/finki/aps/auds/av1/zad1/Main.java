package mk.ukim.finki.aps.auds.av1.zad1;

public class Main {
    public static void main(String[] args) {
        Array2<Integer> koef1 = new Array2<Integer>(7);
        Array2<Integer> koef2 = new Array2<Integer>(7);

        koef1.set(0,3);
        koef2.set(0,3);
        int s = 2;
        int k = 3;
        for(int i=0; i<6; i+=2){
            koef1.set(i,s);
            koef1.set(i+1,k);
            k++;
            s--;
            koef2.set(i,s);
            koef2.set(i+1,k);
        }

        Polinom2 p1 = new Polinom2(koef1);
        Polinom2 p2 = new Polinom2(koef2);

        Polinom2 result = p1.soberiPolinom(p2);
        System.out.println(result.toString());
    }
}
