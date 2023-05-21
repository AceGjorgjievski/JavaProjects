package mk.ukim.finki.sqt.auds.aud_1_Unit_testing;

public class MessageUtil {
    private String message;

    public MessageUtil(String message) {
        this.message = message;
    }

    public String printMessage() {
        System.out.println(this.message);
        return this.message;
    }

    public String salutationMessage() {
        this.message = "Hi!" + this.message;
        System.out.println(this.message);
        return this.message;
    }
}
