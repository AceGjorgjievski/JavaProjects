package mk.ukim.finki.np.labs.lab2;

public class PhoneContact extends Contact{
    private String phoneNumber;
    private Operator operator;


    public PhoneContact(String date,String phoneNumber) {
        super(date);
        this.phoneNumber = phoneNumber;
        char digit = phoneNumber.charAt(2);
        if(digit == '0' || digit == '1' || digit ==  '2'){
            operator = Operator.TMOBILE;
        } else if(digit == '5' || digit == '6'){
            operator = Operator.ONE;
        } else if(digit == '7' || digit == '8'){
            operator = Operator.VIP;
        }
        //else throw exception
    }

    public PhoneContact(PhoneContact pc){
        super(pc);
        phoneNumber = pc.phoneNumber;
        operator = pc.operator;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    public String getType() {
        return "Phone";
    }

    @Override
    public String getContact() {
        return getPhoneNumber();
    }

    @Override
    public boolean getPhone() {
        return false;
    }
}
