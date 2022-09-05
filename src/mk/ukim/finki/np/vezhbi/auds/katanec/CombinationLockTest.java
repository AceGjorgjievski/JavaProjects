package mk.ukim.finki.np.vezhbi.auds.katanec;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class CombinationLock {
    private int digits;
    private boolean isOpen;
    private static int DEFAULT_COMBINATION = 100;

    public CombinationLock(int digits) {
        if(checkValidity(digits)) {
            this.digits = digits;
        } else {
            this.digits = DEFAULT_COMBINATION;
        }
        this.isOpen = false;
    }

    public boolean checkValidity(int digits){
        return (digits > 99 && digits < 1000);
    }

    public boolean open(int digits) {
        this.isOpen = (this.digits == digits);
        return this.isOpen;
    }

    public boolean changeCombo(int oldDigits, int newDigits) {
        boolean isCorrect = this.open(oldDigits);
        if(isCorrect && checkValidity(newDigits)) {
            this.digits = newDigits;
            return true;
        }
        return false;
    }

    public boolean isOpen(){
        return this.isOpen;
    }

    public void lock(){
        this.isOpen = false;
    }
}

public class CombinationLockTest {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        CombinationLock combinationLock = new CombinationLock(234);
        System.out.println("TEST IS OPEN");
        System.out.println(combinationLock.isOpen());

        System.out.println("TEST OPEN");
        System.out.println(combinationLock.open(233));
        System.out.println(combinationLock.open(236));
        System.out.println(combinationLock.open(234));

        combinationLock.lock();

        System.out.println("TEST CHANGE COMBINATION");
        System.out.println(combinationLock.changeCombo(234,777));
        System.out.println(combinationLock.open(100));

        CombinationLock invalidLock = new CombinationLock(1000);

        System.out.println("TEST IS OPEN");
        System.out.println(invalidLock.isOpen());

        System.out.println("TEST OPEN");
        System.out.println(invalidLock.open(200));
        System.out.println(invalidLock.open(300));
        System.out.println(invalidLock.open(100));

        invalidLock.lock();


    }
}
