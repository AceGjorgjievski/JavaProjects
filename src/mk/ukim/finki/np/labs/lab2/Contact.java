package mk.ukim.finki.np.labs.lab2;

public abstract class Contact {
    protected final int day;
    protected final int month;
    protected final int year;

    public Contact(String date) {
        year = Integer.parseInt(date.substring(0,4));
        month = Integer.parseInt(date.substring(5,7));
        day = Integer.parseInt(date.substring(8,10));
    }

    public Contact(Contact c){
        day = c.day;
        month = c.month;
        year = c.year;
    }

    public boolean isNewerThan(Contact c){
        if(year > c.year) return true;
        if(year == c.year && month > c.month) return true;
        if(year == c.year && month == c.month && day > c.day) return true;
        return false;
    }

    public abstract String getType();
    public abstract String getContact();

    public abstract boolean getPhone();
}
