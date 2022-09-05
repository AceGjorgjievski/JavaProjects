package mk.ukim.finki.np.vezhbi.labs.kontaktiodlabs;

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

    public Operator getOperator() {
        String prefix = phone.substring(0,3);

        if(prefix.equals("070") || prefix.equals("071") || prefix.equals("072")) return Operator.TMOBILE;
        else if(prefix.equals("075") || prefix.equals("076")) return Operator.ONE;
        else return Operator.VIP;
    }

    @Override
    public String getType() {
        return "Phone";
    }

    @Override
    public String getContact() {
        return getPhone();
    }
}
