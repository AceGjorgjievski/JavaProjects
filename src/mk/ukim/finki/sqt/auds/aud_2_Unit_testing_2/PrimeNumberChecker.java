package mk.ukim.finki.sqt.auds.aud_2_Unit_testing_2;

public class PrimeNumberChecker {
    public Boolean validate(final Integer primeNumber) {
        for(int i=2; i< Math.sqrt(primeNumber*1.0); i++) {
            if(primeNumber%i == 0) return false;
        }
        return true;
    }
}
