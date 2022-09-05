package mk.ukim.finki.vezhbi.napredno.vzhb2.banka2;

public class PlatinumCheckingAccount extends Account implements InterestBearingAccount{

    public PlatinumCheckingAccount(String owner, long number, double balance) {
        super(owner, number, balance);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.INTEREST;
    }

    @Override
    public void addInterest() {
        addAmount(getBalance()*InterestCheckingAccount.INTEREST*2);
    }
}
