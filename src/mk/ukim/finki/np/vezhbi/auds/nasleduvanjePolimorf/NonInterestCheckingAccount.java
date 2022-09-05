package mk.ukim.finki.np.vezhbi.auds.nasleduvanjePolimorf;


public class NonInterestCheckingAccount extends Account {
    public NonInterestCheckingAccount(String holdersName, int id, double currentBalance) {
        super(holdersName, id, currentBalance);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.NON_INTEREST;
    }
}
