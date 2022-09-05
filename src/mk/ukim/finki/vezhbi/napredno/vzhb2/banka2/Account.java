package mk.ukim.finki.vezhbi.napredno.vzhb2.banka2;

public abstract class Account {
    private String owner;
    private long number;
    private double balance;

    public Account(String owner, long number, double balance) {
        this.owner = owner;
        this.number = number;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "owner='" + owner + '\'' +
                ", number=" + number +
                ", balance=" + balance +
                '}';
    }

    public void withdrawAmount(double num){
        if(balance >= num){
            balance-=num;
        }
        System.out.println("Ne mozhete da podignete sredstva so navedenata suma");
    }

    public void addAmount(double num){
        balance+=num;
    }
    public abstract AccountType getAccountType();


}
