package mk.ukim.finki.np.vezhbi.labs.bankaodlabs;

import java.util.Objects;

public class FlatAmountProvisionTransaction extends Transaction{
    private String flatProvision;
    public FlatAmountProvisionTransaction(long fromId, long toId, String amount, String flatProvision) {
        super(fromId, toId, "FlatAmount", amount);
        this.flatProvision = flatProvision;
    }

    @Override
    double getProvision() {
        return AmountConverter.fromStringToDouble(flatProvision);
    }

    @Override
    public String getDescription() {
        return "FlatAmount";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlatAmountProvisionTransaction that = (FlatAmountProvisionTransaction) o;
        return Objects.equals(flatProvision, that.flatProvision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flatProvision);
    }
}
