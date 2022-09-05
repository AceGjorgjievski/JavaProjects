package mk.ukim.finki.np.vezhbi.labs.picerijaLabs;

public class ArrayIndexOutOfBоundsException extends Exception{

    public ArrayIndexOutOfBоundsException(int index) {
        super(String.format("Index %d is out of bounds",index));
    }
}
