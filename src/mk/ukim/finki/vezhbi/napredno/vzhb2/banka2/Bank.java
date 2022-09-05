package mk.ukim.finki.vezhbi.napredno.vzhb2.banka2;

import java.util.Arrays;

public class Bank {
    private Account [] accounts;
    private int max;
    private int totalAccounts;

    public Bank(int max) {
        this.max = max;
        accounts = new Account[max];
        totalAccounts = 0;
    }

    public void addAccount(Account account){
        if(totalAccounts == accounts.length){
            accounts = Arrays.copyOf(accounts,max*2);
            max*=2;
        }
        accounts[totalAccounts++] = account;
    }

    public double totalAssets(){
        double suma = 0;
        for(Account account : accounts){
            suma += account.getBalance();
        }
        return suma;
    }

    public void addInterestToAllAccounts(){
        for(Account account : accounts){
            if(account.getAccountType().equals(AccountType.INTEREST)){
                InterestBearingAccount ib = (InterestBearingAccount) account;
                ib.addInterest();

            }
        }
    }
}
