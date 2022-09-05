package mk.ukim.finki.np.vezhbi.za_juni.tel_imenik;

public class InvalidNameException extends Exception{
    public String name;

    public InvalidNameException(String name) {
        super();
        this.name = name;
    }
}
