package mk.ukim.finki.np.labs.lab2;

public class EmailContact extends Contact{

    private String email;

    public EmailContact(String date,String email) {
        super(date);
        this.email = email;
    }

    public EmailContact(EmailContact e){
        super(e);
        email = e.email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getType() {
        return "Email";
    }

    @Override
    public String getContact() {
        return getEmail();
    }

    @Override
    public boolean getPhone() {
        return false;
    }
}
