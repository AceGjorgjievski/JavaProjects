package mk.ukim.finki.aps.auds.av0.Generici;

public class Televizor {
    private String proizvoditel;
    private int ekran;
    private boolean isSmart;

    public Televizor() {
    }

    public Televizor(String proizvoditel, int ekran, boolean isSmart) {
        this.proizvoditel = proizvoditel;
        this.ekran = ekran;
        this.isSmart = isSmart;
    }



    @Override
    public String toString() {
        return "Televizor{" +
                "proizvoditel='" + proizvoditel + '\'' +
                ", ekran=" + ekran +
                ", isSmart=" + isSmart +
                '}';
    }

    public String getProizvoditel() {
        return proizvoditel;
    }

    public void setProizvoditel(String proizvoditel) {
        this.proizvoditel = proizvoditel;
    }

    public int getEkran() {
        return ekran;
    }

    public void setEkran(int ekran) {
        this.ekran = ekran;
    }

    public boolean isSmart() {
        return isSmart;
    }

    public void setSmart(boolean smart) {
        isSmart = smart;
    }

}
