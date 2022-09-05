package mk.ukim.finki.np.vezhbi.labs.kontaktiodlabs;

public abstract class Contact {
    private String date;
    private int year;
    private int month;
    private int day;

    public Contact(String date) {
        this.date = date;
        String [] parts = date.split("-");
        this.year = Integer.parseInt(parts[0]);
        this.month = Integer.parseInt(parts[1]);
        this.day = Integer.parseInt(parts[2]);
    }

    public String getDate() {
        return date;
    }

    public boolean isNewerThan(Contact other) {
        if(this.year > other.year) return true;
        else if(this.year < other.year) return false;
        else if(this.month > other.month) return true;
        else if(this.month < other.month) return false;
        else if(this.day > other.day) return true;
        else return false;
    }

    public abstract String getType();
    public abstract String getContact();
}
