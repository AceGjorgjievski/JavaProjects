package mk.ukim.finki.np.vezhbi.labs.bankaodlabs;

import java.util.Random;

public class Account {
    private String username;
    private long id;
    private String balance;
    private static Random RANDOM = new Random();

    public Account(String username, String balance) {
        this.balance = balance;
        this.username = username;
        this.id = RANDOM.nextLong();
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }

    public String getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        //Name:Andrej Gajduk\n Balance:20.00$\n
        return String.format("Name:%s\nBalance:%s",username,balance);
    }
}
