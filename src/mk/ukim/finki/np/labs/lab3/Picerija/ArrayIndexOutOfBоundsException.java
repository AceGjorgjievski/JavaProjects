package mk.ukim.finki.np.labs.lab3.Picerija;

public class ArrayIndexOutOfBоundsException extends Exception {
    public ArrayIndexOutOfBоundsException(int idx) {
        super(String.format("%d is invalid index for removing an item."));
    }
}
