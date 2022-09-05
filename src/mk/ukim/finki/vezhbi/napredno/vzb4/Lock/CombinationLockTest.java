package mk.ukim.finki.vezhbi.napredno.vzb4.Lock;


class InvalidCombinationException extends Exception {
    public InvalidCombinationException(int combination) {
        super(String.format(" %d is invalid combination for unlocking",combination));
    }
}

public class CombinationLockTest {
    private int combination;
    private boolean isOpen;

    public CombinationLockTest(int combination) throws InvalidCombinationException {
        if(isValidCombination(combination)) {
            this.combination = combination;
            isOpen = false;
        }
        else throw new InvalidCombinationException(combination);
    }

    private boolean isValidCombination(int combination) {
        return combination>=100 && combination <=999;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean open(int combination) {
        this.isOpen = isValidCombination(combination) && combination == this.combination;
        return this.isOpen;
    }

    public boolean changeCombo(int oldCombination,int newCombination) throws InvalidCombinationException {
        if(open(oldCombination) && oldCombination == this.combination && isValidCombination(newCombination)){
            this.combination = newCombination;
            this.isOpen = false;
            return true;
        }
        throw new InvalidCombinationException(oldCombination);
    }

    public void lock(){
        this.isOpen = false;
    }

    public static void main(String[] args) {
        CombinationLockTest combinationLockTest = null;

        try {
            combinationLockTest = new CombinationLockTest(123);
        } catch (InvalidCombinationException e) {
            e.printStackTrace();
        }

        System.out.println(combinationLockTest.open(111));
        System.out.println(combinationLockTest.open(123));

        System.out.println(combinationLockTest.isOpen());

        try {
            System.out.println(combinationLockTest.changeCombo(123,777));
            System.out.println(combinationLockTest.changeCombo(133,777));
        } catch (InvalidCombinationException e) {
            e.printStackTrace();
        }


    }

}
