package mk.ukim.finki.np.vezhbi.za_juni.kontakti;

public abstract class Contact implements Comparable<Contact> {
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

    public boolean isNewerThan(Contact other) {
        if(this.year < other.year) {
            return false;
        } else if(this.year > other.year) {
            return true;
        } else if(this.month < other.month) {
            return false;
        } else //this.day > other.day
            if(this.month > other.month) {
            return true;
        } else return this.day >= other.day;
    }
    public abstract String getType();
}
