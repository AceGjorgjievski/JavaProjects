package mk.ukim.finki.np.vezhbi.za_juni.banka;

import java.util.Arrays;
import java.util.Objects;

public class Bank {
    private String name;
    private Account[] accounts;
    private double TOTAL_TRANSFERS = 0;
    private double TOTAL_PROVISIONS = 0;
    private static boolean FIRST_ENTRY = true;
    private double firstBalance;

    public Bank(String name, Account[] accounts) {
        this.name = name;
        this.accounts = new Account[accounts.length];
        for(int i=0; i<accounts.length; i++) {
            this.accounts[i] = accounts[i];
        }
        //System.arraycopy(accounts, 0, this.accounts, 0, accounts.length);
    }

    public boolean makeTransaction(Transaction t) {
        Account from = this.getAccount(t.getFromId());
        Account to = this.getAccount(t.getToId());

        if (from == null || to == null) {
            return false;
        }

        double fromAmount = AmountConverter.stringToDouble(from.getBalance());
        double transactionAmount = AmountConverter.stringToDouble(t.getAmount()) + t.getProvision();

        if (transactionAmount > fromAmount) return false;

        double fromResult = fromAmount - transactionAmount;
        from.setBalance(AmountConverter.doubleToString(fromResult));

        double toAmount = AmountConverter.stringToDouble(to.getBalance());
        double toResult = toAmount + fromAmount;
        to.setBalance(AmountConverter.doubleToString(toResult));


        TOTAL_TRANSFERS += AmountConverter.stringToDouble(t.getAmount());
        TOTAL_PROVISIONS += t.getProvision();


        //double fromAmount = AmountConverter.
        //if(from.getBalance().equals(t.getAmount()))
        return true;
    }

    private Account getAccount(long id) {
        return Arrays.stream(this.accounts).filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    public String totalProvision() {
        return AmountConverter.doubleToString(TOTAL_PROVISIONS);
    }

    public String totalTransfers() {
        return AmountConverter.doubleToString(TOTAL_TRANSFERS);
    }

    public Account[] getAccounts() {
        return this.accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Double.compare(bank.TOTAL_TRANSFERS, TOTAL_TRANSFERS) == 0 && Double.compare(bank.TOTAL_PROVISIONS, TOTAL_PROVISIONS) == 0 && Double.compare(bank.firstBalance, firstBalance) == 0 && Objects.equals(name, bank.name) && Arrays.equals(accounts, bank.accounts);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, TOTAL_TRANSFERS, TOTAL_PROVISIONS, firstBalance);
        result = 31 * result + Arrays.hashCode(accounts);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s\n\n",this.name));

        for(Account a : this.accounts) {
            sb.append(a.toString());
        }
        return sb.toString();
    }
}
