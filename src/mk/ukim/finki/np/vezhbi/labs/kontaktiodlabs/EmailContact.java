package mk.ukim.finki.np.vezhbi.labs.kontaktiodlabs;

public class EmailContact extends Contact{

    private String email;
    public EmailContact(String date, String email) {
        super(date);
        this.email = email;
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
}
