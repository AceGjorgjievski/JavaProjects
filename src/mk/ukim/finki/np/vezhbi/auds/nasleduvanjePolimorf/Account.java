package mk.ukim.finki.np.vezhbi.auds.nasleduvanjePolimorf;

public abstract class Account {
    private String holdersName;
    private int id;
    private static int idSeed = 1000;
    private double currentBalance;

    public Account(String holdersName, int id, double currentBalance) {
        this.holdersName = holdersName;
        this.id = idSeed++;
        this.currentBalance = currentBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void addBalance(double someAmount) {
        this.currentBalance += someAmount;
    }

    public abstract AccountType getAccountType();

    public void removeFromBalance(double someAmount){
        if(currentBalance >= someAmount){
            currentBalance -= someAmount;
        }
    }
}
