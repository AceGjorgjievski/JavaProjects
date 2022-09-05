package mk.ukim.finki.np.vezhbi.labs.bankaodlabs;

public class AmountConverter {
    public static double fromStringToDouble(String amount) {
        String newAmount = amount.substring(0,amount.length()-1);
        return Double.parseDouble(newAmount);
    }
    public static String fromDoudleToString(double amount){
        return String.format("%.2f$",amount);
    }
}
