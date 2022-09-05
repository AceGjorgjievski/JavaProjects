package mk.ukim.finki.np.vezhbi.za_juni.banka;

public class AmountConverter {

    public static String flatAmountConverter(String amount, String provision) {
        String first = amount.substring(0,amount.length()-1).split("\\.")[0];
        String second = provision.substring(0,provision.length()-1).split("\\.")[0];
        double one = Double.parseDouble(first);
        double two = Double.parseDouble(second);

        double sum = one+two;
        return String.format("%.2f$",sum);
    }

    public static int flatPercentConverter(String amount, int centsPerDollar) {
        int first = Integer.parseInt(amount.substring(0, amount.length()-1).split("\\.")[0]);
        int sum = first/centsPerDollar;
        return first+sum;
    }

    public static double stringToDouble(String amount) {
        return Double.parseDouble(amount.substring(0, amount.length()-1));
    }

    public static String doubleToString(double amount) {
        return String.format("%.2f$",amount);
    }
}
