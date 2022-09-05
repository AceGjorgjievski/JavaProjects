package mk.ukim.finki.vezhbi.napredno.vzhb2.banka2;

public class NonInterestCheckingAccount extends Account {

    public NonInterestCheckingAccount(String owner, long number, int balance) {
        super(owner, number, balance);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.NON_INTEREST;
    }
}
