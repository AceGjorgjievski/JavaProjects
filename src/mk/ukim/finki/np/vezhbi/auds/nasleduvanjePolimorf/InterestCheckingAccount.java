package mk.ukim.finki.np.vezhbi.auds.nasleduvanjePolimorf;

public class InterestCheckingAccount extends Account implements InterestBearingAccount{
    public static final double PERCENTAGE = 0.03;

    public InterestCheckingAccount(String holdersName, int acoountNumber, double currentBalance) {
        super(holdersName, acoountNumber, currentBalance);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.INTEREST;
    }

    @Override
    public void addInterest() {
        this.addBalance(getCurrentBalance()*PERCENTAGE);
    }
}
