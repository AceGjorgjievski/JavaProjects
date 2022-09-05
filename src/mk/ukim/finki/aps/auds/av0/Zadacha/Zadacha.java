package mk.ukim.finki.aps.auds.av0.Zadacha;

public class Zadacha {
    private String opis;
    private int brChasovi;
    private boolean status;

    public Zadacha(String opis, int brChasovi, boolean status) {
        this.opis = opis;
        this.brChasovi = brChasovi;
        this.status = status;
    }

    public Zadacha() {
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getBrChasovi() {
        return brChasovi;
    }

    public void setBrChasovi(int brChasovi) {
        this.brChasovi = brChasovi;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Zadacha{" +
                "opis='" + opis + '\'' +
                ", brChasovi=" + brChasovi +
                ", status=" + status +
                '}';
    }
}
