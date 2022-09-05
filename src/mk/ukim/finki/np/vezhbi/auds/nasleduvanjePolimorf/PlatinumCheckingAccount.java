package mk.ukim.finki.np.vezhbi.auds.nasleduvanjePolimorf;

public class PlatinumCheckingAccount extends InterestCheckingAccount {

    public PlatinumCheckingAccount(String holdersName, int acoountNumber, double currentBalance) {
        super(holdersName, acoountNumber, currentBalance);
    }

    @Override
    public void addInterest() {
        this.addBalance(getCurrentBalance()*PERCENTAGE*2);
    }
}
