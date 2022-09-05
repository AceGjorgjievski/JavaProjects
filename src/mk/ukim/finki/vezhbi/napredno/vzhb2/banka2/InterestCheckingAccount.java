package mk.ukim.finki.vezhbi.napredno.vzhb2.banka2;

public class InterestCheckingAccount extends Account implements InterestBearingAccount {

    public static final double INTEREST = 0.03;

    public InterestCheckingAccount(String owner, long number, double balance) {
        super(owner, number, balance);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.INTEREST;
    }

    @Override
    public void addInterest() {
        addAmount(getBalance()*INTEREST);
    }
}
