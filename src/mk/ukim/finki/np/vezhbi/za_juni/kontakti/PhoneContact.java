package mk.ukim.finki.np.vezhbi.za_juni.kontakti;

import org.jetbrains.annotations.NotNull;

public class PhoneContact extends Contact{
    private String phone;
    private Operator operator;
    public PhoneContact(String date, String phone) {
        super(date);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean isNewerThan(Contact other) {
        return false;
    }

    public Operator getOperator() {
        char contactChar = this.phone.charAt(2);
        if(contactChar == 0 || contactChar == 1 || contactChar == 2) {
            return Operator.TMOBILE;
        } else if(contactChar == 5 || contactChar == 6) {
            return Operator.ONE;
        } else {
            return Operator.VIP;
        }
    }

    @Override
    public String getType() {
        return "Phone";
    }

    @Override
    public int compareTo(@NotNull Contact o) {
        return 0;
    }
}
