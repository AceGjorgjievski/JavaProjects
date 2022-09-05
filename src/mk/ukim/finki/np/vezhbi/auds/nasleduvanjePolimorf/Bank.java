package mk.ukim.finki.np.vezhbi.auds.nasleduvanjePolimorf;

import java.util.Arrays;

public class Bank {
    private Account [] accounts;
    private int maxAccounts;
    private int totalAccounts;

    public Bank(int maxAccounts) {
        this.maxAccounts = maxAccounts;
        accounts = new Account[maxAccounts];
        this.totalAccounts = 0;
    }

    public double totalAssets(){
        double total = 0;
        for(Account a : accounts) {
            total += a.getCurrentBalance();
        }
        return total;
    }

    public void addAccount(Account other) {
        if(this.totalAccounts == this.maxAccounts) {
            accounts = Arrays.copyOf(accounts,maxAccounts*2);
            maxAccounts *=2;
        }
        accounts[totalAccounts++] = other;
    }

    public void addInterestToAllAccounts(){
        for(Account account : accounts) {
            if(account.getAccountType().equals(AccountType.INTEREST)) {
                InterestBearingAccount interestBearingAccount = (InterestBearingAccount) account;
                interestBearingAccount.addInterest();
            }
        }
    }
}
