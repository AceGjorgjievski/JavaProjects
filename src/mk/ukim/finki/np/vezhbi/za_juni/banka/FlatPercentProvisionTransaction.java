package mk.ukim.finki.np.vezhbi.za_juni.banka;

import java.util.Objects;

public class FlatPercentProvisionTransaction extends Transaction{
    private int centsPerDollar;
    public FlatPercentProvisionTransaction(long fromId, long toId, String amount, int centsPerDollar) {
        super(fromId, toId, "FlatPercent", amount);
        this.centsPerDollar = centsPerDollar;
    }

    @Override
    public String getDescription() {
        return "FlatPercent";
    }

    @Override
    public double getProvision() {
        return (AmountConverter.stringToDouble(this.getAmount()) * (this.centsPerDollar/100.0));
    }

    public int getPercent() {
        return this.centsPerDollar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlatPercentProvisionTransaction that = (FlatPercentProvisionTransaction) o;
        return centsPerDollar == that.centsPerDollar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(centsPerDollar);
    }
}
