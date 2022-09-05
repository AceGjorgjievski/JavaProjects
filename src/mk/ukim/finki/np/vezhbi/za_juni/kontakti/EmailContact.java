package mk.ukim.finki.np.vezhbi.za_juni.kontakti;

import org.jetbrains.annotations.NotNull;

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
    public boolean isNewerThan(Contact other) {
        return false;
    }

    @Override
    public String getType() {
        return "Email";
    }

    @Override
    public int compareTo(@NotNull Contact o) {
        return 0;
    }
}
