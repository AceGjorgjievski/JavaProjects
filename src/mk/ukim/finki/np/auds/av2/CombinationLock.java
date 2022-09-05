package mk.ukim.finki.np.auds.av2;

public class CombinationLock {
    private int combination;
    private boolean isOpen;

    public CombinationLock(int combination) {
        this.combination = combination;
        this.isOpen = false;
    }

    public boolean open(int combination){
        if(this.combination == combination){
            this.isOpen = true;
        }
        return this.isOpen;
    }

    public boolean changeCombo(int oldCombination, int newCombination){
        if(this.open(oldCombination)){
            this.combination = newCombination;
        }
        return this.isOpen;
    }
    public void lock(){
        this.isOpen = false;
    }


    public static void main(String[] args) {
        CombinationLock lock = new CombinationLock(2345);

        System.out.println("TEST IS OPEN");
        System.out.println(lock.isOpen);

        System.out.println("TEST OPEN");
        System.out.println(lock.open(222));
        System.out.println(lock.open(2335));
        System.out.println(lock.open(2345));

        System.out.println("TEST CHANGE COMBO");
        System.out.println(lock.changeCombo(233,262));
        System.out.println(lock.changeCombo(234,262));
    }
}
