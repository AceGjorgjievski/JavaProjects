package mk.ukim.finki.np.vezhbi.labs.bankaodlabs;

public class Bank {
    private Account [] accounts;
    private String bankName;
    private double totalTransfers;
    private double totalProvisions;

    public Bank(String name, Account [] accounts) {
        this.bankName = name;
        this.accounts = new Account[accounts.length];
        System.arraycopy(accounts, 0, this.accounts, 0, accounts.length);
    }

    public boolean makeTransaction(Transaction other) {
        Account from = findAccount(other.getFromId());
        Account to = findAccount(other.getToId());

        if(from == null || to == null) return false;

        return false;

    }

    private Account findAccount(long id) {
        for(int i=0; i<accounts.length; i++) {
            if(accounts[i].getId() == id) return accounts[i];
        }
        return null;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public String totalProvision() {
        return AmountConverter.fromDoudleToString(totalProvisions);
    }

    public String totalTransfers() {
        return AmountConverter.fromDoudleToString(totalTransfers);
    }
}
