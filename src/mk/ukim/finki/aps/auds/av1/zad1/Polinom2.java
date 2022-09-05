package mk.ukim.finki.aps.auds.av1.zad1;

public class Polinom2 {
    Array2<Integer> koeficient;

    public Polinom2(Array2<Integer> koeficient) {
        this.koeficient = koeficient;
    }

    public void setKoeficient(Array2<Integer> koeficient) {
        this.koeficient = koeficient;
    }

    public Array2<Integer> getKoeficient() {
        return koeficient;
    }

    @Override
    public String toString() {
        String s = new String();
        for(int i=1;i<koeficient.get(0)*2;i++){//golemina na polinom 2m+1
            s += koeficient.get(i+1) + "x^" + koeficient.get(i) + " ";
        }
        return s;
    }

    public Polinom2 soberiPolinom(Polinom2 p){
        Polinom2 result;

        Array2<Integer> koeficient = this.koeficient;
        Array2<Integer> koeficient2 = p.getKoeficient();
        int n = koeficient.get(0);
        int m = koeficient2.get(0);
        Array2<Integer> koeficientResult = new Array2<Integer>(n*2 +m*2 +1);

        int i=1, j=1, k=1;

        while (i<=2*n && j<=2*m){
            if(koeficient.get(i) == koeficient2.get(j)){
                koeficientResult.set(k+1,koeficient.get(i+1) + koeficient2.get(j+1));
                if(koeficientResult.get(k+1) != null){
                    koeficientResult.set(k,koeficient.get(i));
                    k+=2;
                }
                i+=2;
                j+=2;
            }
            else {
                if(koeficient.get(i) < koeficient2.get(j)){
                    koeficientResult.set(k+1,koeficient2.get(j+1));
                    koeficientResult.set(k,koeficient2.get(j));
                    k+=2;
                    j+=2;
                }
                else {
                    koeficientResult.set(k+1,koeficient2.get(i+1));
                    koeficientResult.set(k,koeficient2.get(i));
                    k+=2;
                    j+=2;
                }
            }

        }

        while (i<=2*n){
            koeficientResult.set(k+1,koeficient.get(i+1));
            koeficientResult.set(k,koeficient.get(i));
            k+=2;
            i+=2;
        }

        while (j<=2*m){
            koeficientResult.set(k+1,koeficient.get(j+1));
            koeficientResult.set(k,koeficient.get(j));
            k+=2;
            j+=2;
        }

        koeficientResult.set(0,k/2);
        result = new Polinom2(koeficientResult);
        return result;
    }
}
